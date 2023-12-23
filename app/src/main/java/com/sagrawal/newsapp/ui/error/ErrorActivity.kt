package com.sagrawal.newsapp.ui.error

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sagrawal.newsapp.databinding.FragmentErrorBinding

class ErrorActivity : Activity() {


    private lateinit var binding: FragmentErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tryAgainBtn.setOnClickListener {
            setResult(RESULT_OK, null)
            finish()
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ErrorActivity::class.java)
        }
    }
}