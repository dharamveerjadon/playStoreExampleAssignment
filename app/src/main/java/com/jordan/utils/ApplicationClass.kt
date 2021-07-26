package com.jordan.utils

import android.app.Application

class ApplicationClass : Application() {

    companion object {
        private val TAG = ApplicationClass::class.java.simpleName
    }

}