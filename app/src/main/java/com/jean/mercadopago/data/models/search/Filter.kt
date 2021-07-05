package com.jean.mercadopago.data.models.search

data class Filter(
    var id: String? = null,
    var name: String? = null,
    var type: String? = null,
    var values: List<FilterRow>? = null
)