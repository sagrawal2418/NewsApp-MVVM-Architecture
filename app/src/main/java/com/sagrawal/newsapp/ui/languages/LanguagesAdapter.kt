package com.sagrawal.newsapp.ui.languages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.databinding.LanguagesItemLayoutBinding
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity

class LanguagesAdapter(
    private val context: Context,
    private val languages: ArrayList<Language>
) : RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguagesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, language: Language) {
            binding.languageBtn.text = language.name

            binding.languageBtn.setOnClickListener {
                context.startActivity(
                    TopHeadlineActivity.getStartIntent(
                        it.context,
                        NewsRequest(language = language.code)
                    )
                )
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