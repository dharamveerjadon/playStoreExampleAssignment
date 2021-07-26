package com.jordan.home.viewmodel

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carry.mobile.retrofit.ApiClient
import com.carry.mobile.retrofit.ApiService
import com.jordan.home.model.RowDataResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class CommonViewModel : ViewModel() {

    val rowResponse: MutableLiveData<RowDataResponse> = MutableLiveData()
    private val apiService: ApiService by lazy { ApiClient.create() }

    fun getRows(context: FragmentActivity) {
        val observable = apiService.fetchRows()

        observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    rowResponse.value = it

                },
                {
                    if (it is HttpException) {
                        val errorBody = it.response()?.errorBody()?.string()
                        val jsonObject = JSONObject(errorBody)
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

    }
}