package com.jordan.home.model

import com.google.gson.annotations.SerializedName

data class RowDataAdapterModel(
    @SerializedName("name")
    val name: String, @SerializedName("list")
    val list: List<RowInnerData>
): Comparable<RowDataAdapterModel> {
    override fun compareTo(other: RowDataAdapterModel): Int = this.name.compareTo(other.name)
    override fun toString(): String = "$name"
}

