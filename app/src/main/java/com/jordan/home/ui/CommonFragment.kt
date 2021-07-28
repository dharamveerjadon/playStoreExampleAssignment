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
import com.jordan.home.model.RowDataAdapterModel
import com.jordan.home.viewmodel.CommonViewModel
import java.util.*
import java.util.Collections.sort

class CommonFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var list: List<RowData> = listOf()
    private var rowDataList: List<RowDataAdapterModel> = listOf()
    var rowlist: MutableList<RowDataAdapterModel> = mutableListOf()
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
        observeResponseforHorizontalRowData()
        observeResponse()

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
            rowlist.clear()
            list = it
            for (item in it) {
                fetchRowData(item.name, 10, 0)
            }
        })

        viewModel.rowResponse.removeObservers(this)
    }

    private fun observeResponseforHorizontalRowData() {

        viewModel.rowColumnResponse.observe(viewLifecycleOwner, {

            rowlist.add(it)

            if (rowlist.size == list.size) {
                bindRecyclerView()
            }

        })
        viewModel.rowColumnResponse.removeObservers(this)
    }

    private fun bindRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = HomeMainAdapter(rowlist, requireContext(), this)
        spinnerView.visibility = View.GONE
    }

    fun fetchRowData(row_id: String, limit: Int, skip: Int) {

        val url =
            "http://206.189.139.221:5252/api/$row_id/items?limit=$limit&skip=$skip"
        viewModel.getRowsColumnData(requireActivity(), url, row_id)

    }
}
