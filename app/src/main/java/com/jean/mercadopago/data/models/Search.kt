package com.jean.mercadopago.data.models

import com.google.gson.annotations.SerializedName
import com.jean.mercadopago.data.models.search.Filter
import com.jean.mercadopago.data.models.search.FilterRow
import com.jean.mercadopago.data.models.search.Paging
import com.jean.mercadopago.data.models.search.Result
// Search Pojo class model
data class Search(
    @SerializedName("site_id")
    var site_id: String? = null,
    @SerializedName("query")
    var query: String? = null,
    @SerializedName("paging")
    var paging: Paging? = null,
    @SerializedName("results")
    var results: List<Result>? = null,
    @SerializedName("secondary_results")
    var secondary_results: List<Any>? = null,
    @SerializedName("related_results")
    var related_results:List<Any>? = null,
    @SerializedName("sort")
    var sort: FilterRow? = null,
    @SerializedName("available_sorts")
    var available_sorts:List<FilterRow>? = null,
    @SerializedName("filters")
    var filters:List<Filter>? = null,
    @SerializedName("available_filters")
    var available_filters:List<Filter>? = null
)


