package com.jordan

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jordan.common.ui.OtherFragment
import com.jordan.home.ui.HomeFragment
import com.jordan.utils.ManageFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        ManageFragment.replaceFrag(this, HomeFragment(), "")


        bottomNavigationView?.setOnNavigationItemSelectedListener { onNavigationItemSelected(it) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                ManageFragment.replaceFrag(this, HomeFragment(), "")
            }
            R.id.assistant -> {
                ManageFragment.replaceFrag(this, OtherFragment(), "")
            }
            R.id.notification -> {
                ManageFragment.replaceFrag(this, OtherFragment(), "")
            }
            R.id.settings -> {
                ManageFragment.replaceFrag(this, OtherFragment(), "")
            }

        }
        return true
    }
}