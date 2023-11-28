package com.sagrawal.newsapp.ui.languages

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.databinding.CountriesItemLayoutBinding
import com.sagrawal.newsapp.databinding.LanguagesItemLayoutBinding
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity
import com.sagrawal.newsapp.utils.AppConstant.INTENT_EXTRA_LANGUAGE_SOURCE_ID
import com.sagrawal.newsapp.utils.AppConstant.INTENT_EXTRA_NEWS_SOURCE_ID

class LanguagesAdapter(
    private val context: Context,
    private val languages: ArrayList<Language>
) : RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguagesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, language: Language) {
            binding.languageBtn.text = language.name

            binding.languageBtn.setOnClickListener {
                val intent = Intent(it.context, TopHeadlineActivity::class.java)
                intent.putExtra(INTENT_EXTRA_LANGUAGE_SOURCE_ID, language.code)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LanguagesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = languages.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(context, languages[position])

    fun addData(list: List<Language>) {
        languages.addAll(list)
    }
}