package com.jean.mercadopago.screens.home.repository

import com.jean.mercadopago.data.models.NetworkModel
import javax.inject.Inject
//Repository for consume of services of home
class HomeRepository  @Inject constructor(private val api: NetworkModel) {
    suspend fun fetchSearchList(site:String,search:String) = api.getSearch(site,search)
}