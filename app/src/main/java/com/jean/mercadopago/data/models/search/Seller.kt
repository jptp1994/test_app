package com.jean.mercadopago.data.models.search


data class Seller(
    var id: Int = 0,
    var power_seller_status: String? = null,
    var car_dealer: Boolean = false,
    var real_estate_agency: Boolean = false,
    var tags: List<Any>? = null
)