package com.jean.mercadopago.screens.splash.repository

import com.jean.mercadopago.data.models.NetworkModel
import javax.inject.Inject

class SplashRepository  @Inject constructor(private val api: NetworkModel) {

    suspend fun fetchCountriesList() = api.getCountries()
}