package com.sagrawal.newsapp.ui.newssources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.databinding.NewsSourceItemLayoutBinding
import com.sagrawal.newsapp.utils.ItemClickListener

class NewsSourcesAdapter(
    private val newsSources: ArrayList<NewsSource>
) : RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsSource: NewsSource, itemClickListener: ItemClickListener<String>) {
            binding.newsSourceBtn.text = newsSource.name

            binding.newsSourceBtn.setOnClickListener {
                newsSource.id?.let { it1 -> itemClickListener(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            NewsSourceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = newsSources.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsSources[position], itemClickListener)

    fun addData(list: List<NewsSource>) {
        newsSources.addAll(list)
    }
}