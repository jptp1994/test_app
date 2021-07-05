package com.jean.mercadopago.data.models.search

data class Attribute(
    var name: String? = null,
    var value_id: String? = null,
    var value_name: String? = null,
    var value_struct: ValueStruct? = null,
    var attribute_group_id: String? = null,
    var attribute_group_name: String? = null,
    var source: Long = 0,
    var id: String? = null
)