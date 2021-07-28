package com.jordan.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordan.R
import com.jordan.home.model.RowData
import com.jordan.home.model.RowDataAdapterModel
import com.jordan.home.ui.CommonFragment

class HomeMainAdapter(
    val rowData: List<RowDataAdapterModel>,
    var context: Context,
    val listener: CommonFragment
) : RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout_main_home, parent, false)
        return ViewHolder(v, context, listener)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HomeMainAdapter.ViewHolder, position: Int) {
        holder.bindItems(rowData[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return rowData.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View, var context: Context, val listener: CommonFragment) :
        RecyclerView.ViewHolder(itemView) {

        fun bindItems(rowData: RowDataAdapterModel) {
            val textViewName = itemView.findViewById(R.id.txt_row) as TextView
            textViewName.text = rowData.name

            val recyclerView = itemView.findViewById(R.id.rv_rows) as RecyclerView
            //setting recycler to horizontal scroll
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

           // listener.fetchRowData(rowData.name, 10, 0)
            recyclerView.adapter = HomeCustomAdapter(rowData.list, context)

        }
    }
}