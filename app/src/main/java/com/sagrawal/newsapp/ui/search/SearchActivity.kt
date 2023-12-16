package com.sagrawal.newsapp.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagrawal.newsapp.NewsApplication
import com.sagrawal.newsapp.databinding.ActivitySearchBinding
import com.sagrawal.newsapp.di.component.DaggerActivityComponent
import com.sagrawal.newsapp.di.module.ActivityModule
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.error.ErrorActivity
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineAdapter
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject


class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var searchSourcesViewModel: SearchSourcesViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        setupObserver()
    }


    private fun setupUI() {
        val recyclerView = binding.searchRv
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        adapter.itemClickListener = {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it))
        }

        val searchView = binding.searchView
        searchView.addTextChangedListener(object : TextWatcher {
            private var timer = Timer()
            private val DELAY: Long = 1000 // Milliseconds

            override fun afterTextChanged(s: Editable) {

                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            // task HERE
                            adapter.addData(ArrayList())
                            searchSourcesViewModel.fetchNewsByQueries(s.toString())
                        }
                    },
                    DELAY
                )
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            searchSourcesViewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.differ.submitList(it.data.toList())
                        binding.searchRv.visibility = View.VISIBLE
                    }

                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.searchRv.visibility = View.GONE
                    }

                    is UiState.Error -> {
                        //Handle Error
                        binding.progressBar.visibility = View.GONE
                        resultLauncher.launch(ErrorActivity.getStartIntent(this@SearchActivity))
                    }
                }
            }

        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                searchSourcesViewModel.fetchNewsByQueries("")
            }
        }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}