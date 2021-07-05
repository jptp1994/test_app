package com.jean.mercadopago.data.models.search



data class Installments(
    var quantity: Int = 0,
    var amount: Double = 0.0,
    var rate: Double = 0.0,
    var currency_id: String? = null
)