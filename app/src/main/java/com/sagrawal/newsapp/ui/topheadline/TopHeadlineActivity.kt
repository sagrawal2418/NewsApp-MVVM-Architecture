package com.sagrawal.newsapp.ui.topheadline

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagrawal.newsapp.NewsApplication
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.databinding.ActivityTopHeadlineBinding
import com.sagrawal.newsapp.di.component.DaggerActivityComponent
import com.sagrawal.newsapp.di.module.ActivityModule
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.error.ErrorActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsSourceId = intent.getStringExtra(INTENT_EXTRA_NEWS_SOURCE_ID)
        val languageSourceId = intent.getStringExtra(INTENT_EXTRA_LANGUAGE_SOURCE_ID)
        val countrySourceId = intent.getStringExtra(INTENT_EXTRA_COUNTRY_SOURCE_ID)

        newsListViewModel.init(newsSourceId, languageSourceId, countrySourceId)
        newsListViewModel.makeServiceCall()
        setupUI()
        setupObserver()
    }


    private fun setupUI() {
        val recyclerView = binding.recyclerView
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
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.differ.submitList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            val intent = Intent(this@TopHeadlineActivity, ErrorActivity::class.java)
                            resultLauncher.launch(intent)
                        }
                    }
                }
            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                newsListViewModel.makeServiceCall()
            }
        }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    companion object {

        const val INTENT_EXTRA_COUNTRY_SOURCE_ID = "COUNTRY_SOURCE_ID"
        const val INTENT_EXTRA_LANGUAGE_SOURCE_ID = "LANGUAGE_SOURCE_ID"
        const val INTENT_EXTRA_NEWS_SOURCE_ID = "NEWS_SOURCE_ID"

        fun getStartIntent(context: Context, newsRequest: NewsRequest? = null): Intent {
            return Intent(context, TopHeadlineActivity::class.java).apply {

                newsRequest?.apply {
                    putExtra(INTENT_EXTRA_COUNTRY_SOURCE_ID, country)
                    putExtra(INTENT_EXTRA_LANGUAGE_SOURCE_ID, language)
                    putExtra(INTENT_EXTRA_NEWS_SOURCE_ID, news)
                }

            }
        }
    }
}