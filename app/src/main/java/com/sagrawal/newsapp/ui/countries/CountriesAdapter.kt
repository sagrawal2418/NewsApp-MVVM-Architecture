package com.sagrawal.newsapp.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.NewsRequest
import com.sagrawal.newsapp.databinding.CountriesItemLayoutBinding
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity

class CountriesAdapter(
    private val context: Context,
    private val countries: ArrayList<Country>
) : RecyclerView.Adapter<CountriesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, country: Country) {
            binding.countryBtn.text = country.countryName

            binding.countryBtn.setOnClickListener {
                context.startActivity(
                    TopHeadlineActivity.getStartIntent(
                        it.context,
                        NewsRequest(country = country.countryCode)
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            CountriesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(context, countries[position])

    fun addData(list: List<Country>) {
        countries.addAll(list)
    }
}