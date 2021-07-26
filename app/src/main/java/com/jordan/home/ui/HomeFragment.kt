package com.jordan.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jordan.R
import com.jordan.home.adapter.ViewPagerAdapter
import com.jordan.home.viewmodel.HomeViewModel

class HomeFragment : Fragment(), View.OnClickListener {

    private var searchView: SearchView? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var smileIcon: ImageView? = null

    /* private val vm: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }*/
    private lateinit var model: HomeViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFindId(view)
        registerListener()


    }


    private fun initFindId(view: View) {
        searchView = view.findViewById(R.id.searchView)
        smileIcon = view.findViewById(R.id.user_profile)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.vp_homepage)
    }

    private fun registerListener() {
        smileIcon?.setOnClickListener(this)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Toast.makeText(context, p0, Toast.LENGTH_SHORT).show()
                return false
            }
        })

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        tabLayout!!.addTab(tabLayout!!.newTab().setText("For you"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Top Chart"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Categories"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Recommend"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_START

        val adapter = activity?.let {
            ViewPagerAdapter(
                it,
                childFragmentManager,
                tabLayout!!.tabCount
            )
        }
        viewPager!!.adapter = adapter

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.user_profile -> activity?.let { model.onSmileClick(it) }
            }
        }
    }

}