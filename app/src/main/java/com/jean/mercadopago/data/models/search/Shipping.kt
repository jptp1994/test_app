package com.jean.mercadopago.data.models.search

data class Shipping(
    var free_shipping: Boolean = false,
    var mode: String? = null,
    var tags: List<Any>? = null,
    var logistic_type: String? = null,
    var store_pick_up: Boolean = false
)