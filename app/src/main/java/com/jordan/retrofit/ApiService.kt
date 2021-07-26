package com.carry.mobile.retrofit

import com.jordan.home.model.RowData
import com.jordan.home.model.RowInnerData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("rows")
    fun fetchRows(): Observable<List<RowData>>

    @GET
    fun fetchRowsColumnData(@Url url: String): Observable<List<RowInnerData>>

}