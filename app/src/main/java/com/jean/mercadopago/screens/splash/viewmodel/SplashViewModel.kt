package com.jean.mercadopago.screens.splash.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jean.mercadopago.R
import com.jean.mercadopago.data.models.CountryList
import com.jean.mercadopago.data.singleton.SingletonData
import com.jean.mercadopago.screens.common.BaseViewModel
import com.jean.mercadopago.screens.splash.repository.SplashRepository
import com.jean.mercadopago.utils.GeneralConstants
import com.jean.mercadopago.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val splashRepository: SplashRepository) :
    BaseViewModel() {


    private val _sortedProducts = MutableLiveData<Resource<CountryList>>()
    val sortedProducts: LiveData<Resource<CountryList>> get() = _sortedProducts
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun reloadCountryList(){
        if (SingletonData.countryList == null){
            fetchList()
        }else{
            _sortedProducts.value=sendDataSave()
        }
    }

    fun fetchList() = viewModelScope.launch {
        getCountryList()
    }

    private suspend fun getCountryList() {
        _sortedProducts.postValue(Resource.Loading())
        try {
            val response = splashRepository.fetchCountriesList()
            _sortedProducts.value = handlePicsResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _sortedProducts.postValue(
                        Resource.Error(
                            GeneralConstants.ERROR_RED
                        )
                    )
                }
                else -> {
                    _sortedProducts.postValue(
                        Resource.Error(
                            GeneralConstants.ERROR_PARSEO
                        )
                    )
                }
            }
        }
    }

    private fun sendDataSave(): Resource<CountryList> {
            return Resource.Reload(context.getString(R.string.recharge_reload_success),
                SingletonData.countryList)
        }

    private fun handlePicsResponse(response: Response<CountryList>): Resource<CountryList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                SingletonData.countryList = resultResponse
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}