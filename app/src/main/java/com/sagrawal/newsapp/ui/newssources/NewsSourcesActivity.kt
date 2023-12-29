package com.sagrawal.newsapp.ui.newssources

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.databinding.ActivityNewsSourcesBinding
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.error.ErrorActivity
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsSourcesActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsSourcesViewModel

    @Inject
    lateinit var adapter: NewsSourcesAdapter

    private lateinit var binding: ActivityNewsSourcesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupViewModel() {
        newsListViewModel = ViewModelProvider(this)[NewsSourcesViewModel::class.java]
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
            startActivity(
                TopHeadlineActivity.getStartIntent(
                    this,
                    NewsRequest(news = it)
                )
            )
        }
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
                            resultLauncher.launch(ErrorActivity.getStartIntent(this@NewsSourcesActivity))
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
                newsListViewModel.fetchNews()
            }
        }

    private fun renderList(articleList: List<NewsSource>) {
        adapter.addData(articleList)
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, NewsSourcesActivity::class.java)
        }
    }
}