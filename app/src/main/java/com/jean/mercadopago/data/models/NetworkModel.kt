package com.jean.mercadopago.data.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//Interface Api Services
interface NetworkModel {
    @GET("sites/{sites}/search?")
    suspend fun getSearch(
        @Path("sites") sites: String,
        @Query("q") searchString: String
    ):  Response<Search>

    @GET("sites")
    suspend fun getCountries(): Response<CountryList>
}