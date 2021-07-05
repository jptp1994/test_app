package com.jean.mercadopago.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Model Class Country Response Api
data class Country(
    @SerializedName("default_currency_id")
    var default_currency_id: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null
):Serializable
