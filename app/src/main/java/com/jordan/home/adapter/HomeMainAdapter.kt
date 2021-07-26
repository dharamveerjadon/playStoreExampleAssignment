package com.jordan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jordan.R
import com.jordan.home.model.RowData

class HomeMainAdapter (val rowData: ArrayList<RowData>) : RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout_main_home, parent, false)
        return ViewHolder(v)
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
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(rowData: RowData) {
            val textViewName = itemView.findViewById(R.id.txt_row) as TextView
            textViewName.text = rowData.id.toString()

        }
    }
}