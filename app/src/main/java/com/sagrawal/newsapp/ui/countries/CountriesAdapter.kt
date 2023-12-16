package com.sagrawal.newsapp.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.databinding.CountriesItemLayoutBinding
import com.sagrawal.newsapp.utils.ItemClickListener

class CountriesAdapter(
    private val countries: ArrayList<Country>
) : RecyclerView.Adapter<CountriesAdapter.DataViewHolder>() {


    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, itemClickListener: ItemClickListener<String>) {
            binding.countryBtn.text = country.countryName
            binding.countryBtn.setOnClickListener {
                itemClickListener(country.countryCode)
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
        holder.bind(countries[position], itemClickListener)

    fun addData(list: List<Country>) {
        countries.addAll(list)
    }
}