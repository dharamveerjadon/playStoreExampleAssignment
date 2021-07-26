package com.jordan.home.model

import com.google.gson.annotations.SerializedName

data class RowInnerData(
    @SerializedName("img")
    val img: String, @SerializedName("name")
    val name: String, @SerializedName("size")
    val size: String
)
