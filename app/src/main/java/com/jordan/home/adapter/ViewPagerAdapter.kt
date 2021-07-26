package com.jordan.home.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jordan.home.ui.CommonFragment

class ViewPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return CommonFragment()
            }
            1 -> {
                return CommonFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return CommonFragment()
            }
            else -> return CommonFragment()

        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}