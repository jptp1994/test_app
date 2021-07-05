package com.jean.mercadopago.screens.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jean.mercadopago.R
import com.jean.mercadopago.data.models.search.Result
import com.jean.mercadopago.data.singleton.SingletonData
import com.jean.mercadopago.databinding.FragmentSearchBinding
import com.jean.mercadopago.screens.common.BaseActivity
import com.jean.mercadopago.screens.home.adapters.searchFragment.SearchDetailListener
import com.jean.mercadopago.screens.home.adapters.searchFragment.SearchListAdapter
import com.jean.mercadopago.screens.home.viewmodel.HomeViewModel
import com.jean.mercadopago.utils.Resource

class SearchFragment : Fragment(), SearchDetailListener, SearchView.OnQueryTextListener {

    private val loginViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = loginViewModel
        (requireActivity() as BaseActivity).setBaseViewModel(loginViewModel)
        observeLiveData()
        setListenerFind()
    }
    @SuppressLint("SetTextI18n")
    private fun setAdapterList(results: List<Result>?, total: Int) {
        val contactsAdapter = SearchListAdapter(this)
        if (results != null && total!=0) {
            binding.tvResultsNumber.text=total.toString()+ requireActivity().getString(R.string.results)
            contactsAdapter.updateContacts(results)
            binding.rvSearchList.adapter = contactsAdapter
            showRvResults()
        } else{
            showErrorSearch()
        }
    }

    private fun showRvResults() {
        binding.tvResultsNumber.visibility = View.VISIBLE
        binding.rvSearchList.visibility = View.VISIBLE
        binding.errorSearch.visibility=View.GONE
    }
    private fun showErrorSearch(){
        binding.tvResultsNumber.visibility=View.GONE
        binding.rvSearchList.visibility=View.GONE
        binding.errorSearch.visibility=View.VISIBLE
    }

    //observer for get productlist
    private fun observeLiveData() {
        loginViewModel.productList.observe(requireActivity(), Observer { searchResponse ->
            when (searchResponse) {
                    is Resource.Success -> {
                        hideProgressBar()
                        searchResponse.data?.let { resultList ->
                            setAdapterList(resultList.results, resultList.paging!!.total)
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        searchResponse.message?.let { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }

                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Reload -> {
                        //showProgressBar()
                    }
                }
            })
    }
    //send navigation action
    override fun onClickResult(result: Result) {
        SingletonData.searchSelected=result
        findNavController().navigate(R.id.action_searchFragment_to_searchDetailFragment)
    }
    //set querylistener
    private fun setListenerFind(){
        binding.searchArtist.setOnQueryTextListener(this)
    }

    //function to send Search consume
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            loginViewModel.init(SingletonData.countryName, query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    //Show and hide a progressbar
    private fun hideProgressBar() {
        binding.progressHome.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressHome.visibility = View.VISIBLE
    }

}
