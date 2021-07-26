package com.jordan.home.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    fun onSmileClick(context: Context) {
        Toast.makeText(context, "Hey your profile icon", Toast.LENGTH_SHORT).show()
    }
}