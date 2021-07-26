package com.jordan.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordan.R
import com.jordan.common.ui.SpinnerView
import com.jordan.home.adapter.HomeMainAdapter
import com.jordan.home.model.RowData
import com.jordan.home.model.RowInnerData
import com.jordan.home.viewmodel.CommonViewModel

class CommonFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var list: List<RowInnerData> = listOf()
    private var rowDataList: List<RowData> = listOf()
    var rowlist: MutableList<List<RowInnerData>> = mutableListOf()
    private lateinit var spinnerView: SpinnerView

    companion object {
        fun newInstance() = CommonFragment()
    }

    private val viewModel: CommonViewModel by lazy { ViewModelProvider(this)[CommonViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.common_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFindId(view)
        observeResponse()
        observeResponseforHorizontalRowData()

        viewModel.getRows(requireActivity())
        recyclerView?.adapter = HomeMainAdapter(rowDataList, requireContext(), this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRows(requireActivity())
    }

    private fun initFindId(view: View) {
        recyclerView = view.findViewById(R.id.rv_main)
        spinnerView = view.findViewById(R.id.spinner_view)
    }

    private fun observeResponse() {
        viewModel.rowResponse.observe(viewLifecycleOwner, {
            // Access the RecyclerView Adapter and load the data into it
            rowDataList = it
            spinnerView.visibility = View.GONE
            bindRecyclerView()
        })

        viewModel.rowResponse.removeObservers(this)
    }

    private fun observeResponseforHorizontalRowData() {

        viewModel.rowColumnResponse.observe(viewLifecycleOwner, {
            list = it
            rowlist.add(it)

        })
        viewModel.rowColumnResponse.removeObservers(this)
    }

    private fun bindRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = HomeMainAdapter(rowDataList, requireContext(), this)

    }

    fun fetchRowData(row_id: String, limit: Int, skip: Int) {

        val url =
            "http://206.189.139.221:5252/api/$row_id/items?limit=$limit&skip=$skip"
        viewModel.getRowsColumnData(requireActivity(), url)

    }

    fun getRowColumnData() = list
}