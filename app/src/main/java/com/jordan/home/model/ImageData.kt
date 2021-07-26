package com.jordan.home.model

import com.google.gson.annotations.SerializedName

data class ImageData( @SerializedName("id")
                      val id: Int,
                      @SerializedName("image_name")
                      val imageName: String,
                      @SerializedName("imageUrl")
                      val imageUrl: Int)
