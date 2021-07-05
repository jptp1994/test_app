package com.jean.mercadopago.screens.home.adapters.searchFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.jean.mercadopago.R
import com.jean.mercadopago.data.models.search.Result
import com.jean.mercadopago.databinding.RowSearchBinding
class SearchListAdapter(
    private val searchDetailListener: SearchDetailListener
):
    RecyclerView.Adapter<SearchListAdapter.ContactsViewHolder>() {
    lateinit var context: Context
    private var listContacts = ArrayList<Result>()
    lateinit var binding: RowSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        context = parent.getContext()
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_search,
            parent,
            false
        )
        return ContactsViewHolder(binding)
    }

    override fun getItemCount() = listContacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(listContacts[position])
    }

    fun ImageView.loadUrl(url: String, context: Context) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .into(this)
    }
    fun updateContacts(contacts: List<Result>) {
        listContacts.clear()
        listContacts.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ContactsViewHolder(val binding: RowSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(resultRow: Result) {
            binding.search = resultRow

            //resultRow.thumbnail?.let { binding.imageView2.loadUrl(it,context) }
            binding.itemSearch.setOnClickListener {
                searchDetailListener.onClickResult(resultRow)
            }
        }
    }
}