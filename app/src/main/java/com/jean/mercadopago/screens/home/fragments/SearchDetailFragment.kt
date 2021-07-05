package com.jean.mercadopago.screens.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jean.mercadopago.R
import com.jean.mercadopago.data.singleton.SingletonData
import com.jean.mercadopago.databinding.FragmentSearchDetailBinding
import com.jean.mercadopago.screens.home.viewmodel.HomeViewModel

class SearchDetailFragment : Fragment(), View.OnClickListener {

    private val loginViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_detail, container, false)
        binding.search = SingletonData.searchSelected

        binding.viewModel = loginViewModel
        binding.ivShare.setOnClickListener(this)
        return binding.root
    }

    private fun shareContent() {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, binding.search?.permalink)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
    }

    override fun onClick(v: View?) {
        shareContent()
    }

}