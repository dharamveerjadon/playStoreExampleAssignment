package com.jordan.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordan.R
import com.jordan.home.adapter.HomeMainAdapter
import com.jordan.home.model.RowData
import com.jordan.home.viewmodel.CommonViewModel

class CommonFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance() = CommonFragment()
    }

    private val viewModel: CommonViewModel by lazy { ViewModelProvider(this)[CommonViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.common_fragment, container, false)
        initFindId(view)
        observeResponse()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindRecyclerView()
    }
    private fun initFindId(view: View) {
        recyclerView = view.findViewById(R.id.rv_main)
    }

    private fun observeResponse() {
        viewModel.rowResponse.observe(viewLifecycleOwner, {
            Toast.makeText(context, "it.message", Toast.LENGTH_SHORT).show()
        })
        viewModel.rowResponse.removeObservers(this)
    }

    private fun bindRecyclerView() {
        //crating an arraylist to store users using the data class user
        val rows = ArrayList<RowData>()

        //adding some dummy data to the list
        rows.add(RowData(1))
        rows.add(RowData(2))
        rows.add(RowData(3))
        rows.add(RowData(4))

        // Creates a vertical Layout Manager
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        // Access the RecyclerView Adapter and load the data into it
        recyclerView?.adapter = HomeMainAdapter(rows)
    }
}