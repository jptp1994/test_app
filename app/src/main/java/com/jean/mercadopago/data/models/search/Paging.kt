package com.jean.mercadopago.data.models.search

data class Paging(
    var total: Int = 0,
    var offset: Int = 0,
    var limit: Int = 0,
    var primary_results: Int = 0
)