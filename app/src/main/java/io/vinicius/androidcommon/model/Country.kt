package io.vinicius.androidcommon.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val alpha2Code: String,
    val name: String
)