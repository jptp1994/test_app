package com.jean.mercadopago.data.models.search

import java.util.*

data class Result(
    var id: String? = null,
    var site_id: String? = null,
    var title: String? = null,
    var seller: Seller? = null,
    var price: Double = 0.0,
    var currency_id: String? = null,
    var available_quantity: Int = 0,
    var sold_quantity: Int = 0,
    var buying_mode: String? = null,
    var listing_type_id: String? = null,
    var stop_time: Date? = null,
    var condition: String? = null,
    var permalink: String? = null,
    var thumbnail: String? = null,
    var accepts_mercadopago: Boolean = false,
    var installments: Installments? = null,
    var address: Address? = null,
    var shipping: Shipping? = null,
    var seller_address: SellerAddress? = null,
    var attributes: List<Attribute>? = null,
    var original_price: Any? = null,
    var category_id: String? = null,
    var official_store_id: Int = 0,
    var catalog_product_id: String? = null,
    var tags: List<String>? = null,
    var catalog_listing: Boolean = false
)




