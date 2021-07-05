package com.jean.mercadopago.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jean.mercadopago.data.models.Search
import com.jean.mercadopago.database.dataBaseInter.FindsLogs
import com.jean.mercadopago.database.modelDataBase.FindModel
import com.jean.mercadopago.screens.common.BaseViewModel
import com.jean.mercadopago.screens.home.repository.HomeRepository
import com.jean.mercadopago.utils.GeneralConstants
import com.jean.mercadopago.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository,
                                        private val findsLogs: FindsLogs
)
    : BaseViewModel() {

    private val _sortedProducts = MutableLiveData<Resource<Search>>()
    val productList: LiveData<Resource<Search>> = _sortedProducts

    // consume servicio a traves de corrutina
    fun init(site:String, search:String) = viewModelScope.launch {
        getSearchList(site,search)
    }

    //parsea respuesta de search
    private suspend fun getSearchList(site:String, search:String) {
        Log.i("getSearchList","Loading")
        _sortedProducts.postValue(Resource.Loading())
        try {
            val response = homeRepository.fetchSearchList(site,search)
            _sortedProducts.postValue(handleSearchResponse(response))
        } catch (t: Throwable) {
            Log.e("getSearchList","HAndle error service")
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


    private fun handleSearchResponse(response: Response<Search>): Resource<Search> {
        Log.e("handleSearchResponse","HAndle validation response")
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                resultResponse.results?.let { insertNewFindLog(it.size,resultResponse.query) }
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun insertNewFindLog(size: Int, query: String?) {
        Log.e("insertNewFindLog", "guardar en BD")
        val searchSuccess: FindModel? =null
        searchSuccess?.findSize=size.toString()
        searchSuccess?.findText=query!!
        if (searchSuccess != null) {
            findsLogs.insertFind(searchSuccess)
        }
    }

}