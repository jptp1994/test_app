package com.jean.mercadopago.screens.home.adapters.searchFragment

import com.jean.mercadopago.data.models.search.Result

interface SearchDetailListener {
    fun onClickResult(result: Result)
}