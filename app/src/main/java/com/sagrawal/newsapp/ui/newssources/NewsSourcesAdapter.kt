package com.sagrawal.newsapp.ui.newssources

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.databinding.NewsSourceItemLayoutBinding
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity

class NewsSourcesAdapter(
    private val context: Context,
    private val newsSources: ArrayList<NewsSource>
) : RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, newsSource: NewsSource) {
            binding.newsSourceBtn.text = newsSource.name

            binding.newsSourceBtn.setOnClickListener {
                context.startActivity(
                    TopHeadlineActivity.getStartIntent(
                        it.context,
                        NewsRequest(news = newsSource.id)
                    )
                )
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
        holder.bind(context, newsSources[position])

    fun addData(list: List<NewsSource>) {
        newsSources.addAll(list)
    }
}