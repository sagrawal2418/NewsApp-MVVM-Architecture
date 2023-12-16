package com.sagrawal.newsapp.ui.languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.databinding.LanguagesItemLayoutBinding
import com.sagrawal.newsapp.utils.ItemClickListener

class LanguagesAdapter(
    private val languages: ArrayList<Language>
) : RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: LanguagesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, itemClickListener: ItemClickListener<String>) {
            binding.languageBtn.text = language.name

            binding.languageBtn.setOnClickListener {
                itemClickListener(language.code)
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
        holder.bind(languages[position], itemClickListener)

    fun addData(list: List<Language>) {
        languages.addAll(list)
    }
}