package com.sagrawal.newsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sagrawal.newsapp.databinding.ActivityMainBinding
import com.sagrawal.newsapp.ui.countries.CountriesActivity
import com.sagrawal.newsapp.ui.languages.LanguagesActivity
import com.sagrawal.newsapp.ui.newssources.NewsSourcesActivity
import com.sagrawal.newsapp.ui.search.SearchActivity
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.topHeadlinesBtn.setOnClickListener {
            startActivity(TopHeadlineActivity.getStartIntent(this))
        }

        binding.newsSourcesBtn.setOnClickListener {
            startActivity(NewsSourcesActivity.getStartIntent(this))
        }

        binding.countriesBtn.setOnClickListener {
            startActivity(CountriesActivity.getStartIntent(this))
        }
        binding.languagesBtn.setOnClickListener {
            startActivity(LanguagesActivity.getStartIntent(this))
        }

        binding.searchBtn.setOnClickListener {
            startActivity(SearchActivity.getStartIntent(this))
        }
    }
}