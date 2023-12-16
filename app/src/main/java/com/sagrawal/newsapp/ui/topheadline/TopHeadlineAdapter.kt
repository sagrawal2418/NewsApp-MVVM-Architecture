package com.sagrawal.newsapp.ui.topheadline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.databinding.TopHeadlineItemLayoutBinding
import com.sagrawal.newsapp.utils.ItemClickListener

class TopHeadlineAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<String>


    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, itemClickListener: ItemClickListener<String>) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                itemClickListener(article.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    /*
  here we don't use list.notifyDatasetChanged
  because using that it the recyclerview adapter will always update the whole items even if they are not changed

  to solve this, we use DiffUtil
  it calculates the diff between two list and enable us to only update those items that are different
  also runs in background so don't block the main thread
   */
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // tool that will take the two list and tell the differences
    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(differ.currentList[position], itemClickListener)

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }
}