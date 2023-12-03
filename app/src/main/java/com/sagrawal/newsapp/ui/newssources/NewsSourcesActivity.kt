package com.sagrawal.newsapp.ui.newssources

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagrawal.newsapp.NewsApplication
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.databinding.ActivityNewsSourcesBinding
import com.sagrawal.newsapp.di.component.DaggerActivityComponent
import com.sagrawal.newsapp.di.module.ActivityModule
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.error.ErrorActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListViewModel: NewsSourcesViewModel

    @Inject
    lateinit var adapter: NewsSourcesAdapter

    private lateinit var binding: ActivityNewsSourcesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            val intent = Intent(this@NewsSourcesActivity, ErrorActivity::class.java)
                            resultLauncher.launch(intent)
                        }
                    }
                }
            }
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                newsListViewModel.fetchNews()
            }
        }

    private fun renderList(articleList: List<NewsSource>) {
        adapter.addData(articleList)
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}