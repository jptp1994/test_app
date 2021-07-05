package com.jean.mercadopago.screens.splash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jean.mercadopago.R
import com.jean.mercadopago.data.models.Country
import com.jean.mercadopago.data.models.CountryList
import com.jean.mercadopago.databinding.ItemCountryBinding

class ListCountryAdapter(
    val countryListener: (value: Country) -> Unit,
    countryList: CountryList?
) : RecyclerView.Adapter<ListCountryAdapter.CountryViewHolder>() {

    lateinit var binding: ItemCountryBinding
    private var countryList: MutableList<Country> = countryList!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_country, parent, false)
        return CountryViewHolder(binding)
    }


    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countryList[position]
        holder.bind(item)
    }

  inner class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.modelCountry = country
            binding.clTabDescription.setOnClickListener { countryListener(country) }
        }
    }





}