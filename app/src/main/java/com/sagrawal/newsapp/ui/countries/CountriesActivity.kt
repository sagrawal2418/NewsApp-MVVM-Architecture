package com.sagrawal.newsapp.ui.countries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.databinding.ActivityCountriesBinding
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {

    private lateinit var countriesViewModel: CountriesViewModel

    @Inject
    lateinit var adapter: CountriesAdapter

    private lateinit var binding: ActivityCountriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupViewModel() {
        countriesViewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
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
            startActivity(TopHeadlineActivity.getStartIntent(this, NewsRequest(country = it)))
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.uiState.collect {
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
                            Toast.makeText(this@CountriesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(countriesList: List<Country>) {
        adapter.addData(countriesList)
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, CountriesActivity::class.java)
        }
    }
}