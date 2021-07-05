package com.jean.mercadopago.data.singleton

import com.jean.mercadopago.data.models.CountryList
import com.jean.mercadopago.data.models.search.Result
import com.jean.mercadopago.database.modelDataBase.FindModel

object SingletonData {
    var search = ""
    var countryName = ""
    var countryList: CountryList? = null
    var searchSelected: Result? = null
}