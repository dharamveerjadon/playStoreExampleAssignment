package com.carry.mobile.retrofit

import com.jordan.home.model.ImageDataResponse
import com.jordan.home.model.RowDataResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("images")
    fun fetchImages(): Observable<ImageDataResponse>

    @GET("rows")
    fun fetchRows(): Observable<RowDataResponse>

}