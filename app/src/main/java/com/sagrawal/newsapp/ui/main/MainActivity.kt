package com.sagrawal.newsapp.ui.main

import android.content.Intent
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
            val intent = Intent(this, TopHeadlineActivity::class.java)
            startActivity(intent)
        }

        binding.newsSourcesBtn.setOnClickListener {
            val intent = Intent(this, NewsSourcesActivity::class.java)
            startActivity(intent)
        }

        binding.countriesBtn.setOnClickListener {
            val intent = Intent(this, CountriesActivity::class.java)
            startActivity(intent)
        }
        binding.languagesBtn.setOnClickListener {
            val intent = Intent(this, LanguagesActivity::class.java)
            startActivity(intent)
        }

        binding.searchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}