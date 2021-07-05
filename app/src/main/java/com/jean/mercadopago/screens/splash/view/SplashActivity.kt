package com.jean.mercadopago.screens.splash.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jean.mercadopago.R
import com.jean.mercadopago.data.models.Country
import com.jean.mercadopago.data.models.CountryList
import com.jean.mercadopago.data.singleton.SingletonData
import com.jean.mercadopago.databinding.ActivitySplashBinding
import com.jean.mercadopago.screens.common.BaseActivity
import com.jean.mercadopago.screens.home.view.HomeActivity
import com.jean.mercadopago.screens.splash.adapter.ListCountryAdapter
import com.jean.mercadopago.screens.splash.viewmodel.SplashViewModel
import com.jean.mercadopago.utils.Resource
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private lateinit var countryAdapter: ListCountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        setBaseViewModel(viewModel)
        binding.splashViewModel = viewModel

        viewModel.context=applicationContext

        observeLiveData()

        viewModel.reloadCountryList()

    }


    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

    //Functions for observable list
    private fun observeLiveData() {

        viewModel.sortedProducts.observe(this, Observer { availableVersion ->
                when (availableVersion) {
                    is Resource.Success -> {
                        hideProgressBar()
                        availableVersion.data?.let { picsResponse ->
                            setCountryListToRV(picsResponse)
                            Toast.makeText(applicationContext, "Exitoso", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    is Resource.Error -> {
                        hideProgressBar()
                        availableVersion.message?.let { message ->
                            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                    is Resource.Reload -> {
                        hideProgressBar()
                        SingletonData.countryList?.let { setCountryListToRV(it) }

                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }

        })

    }

    //Work with RV
    private fun setCountryListToRV(countryList: CountryList) {
        countryAdapter = ListCountryAdapter(
            countryListener = {country ->
            setCountry(country)
        }
            , countryList = countryList )
        binding.rvCountry.adapter = countryAdapter

    }

    //Set active country
    private fun setCountry(country: Country) {
        SingletonData.countryName = country.id!!
        Intent(this@SplashActivity, HomeActivity::class.java).also {
            startActivity(it)
        }
    }
}
