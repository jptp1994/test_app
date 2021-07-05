package com.jean.mercadopago.data.models.search


data class SellerAddress(
    var id: String? = null,
    var comment: String? = null,
    var address_line: String? = null,
    var zip_code: String? = null,
    var countrySearch: CountrySearch? = null,
    var state: State? = null,
    var city: City? = null,
    var latitude: String? = null,
    var longitude: String? = null
)