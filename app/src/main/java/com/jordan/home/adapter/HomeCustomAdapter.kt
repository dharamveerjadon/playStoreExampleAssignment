package com.jordan.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordan.R
import com.jordan.home.model.RowInnerData

class HomeCustomAdapter(val rowInnerData: List<RowInnerData>, var context: Context) :
    RecyclerView.Adapter<HomeCustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout_horizontal_item, parent, false)
        return ViewHolder(v, context)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HomeCustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(rowInnerData[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return rowInnerData.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(rowInnerData: RowInnerData) {
            val textViewName = itemView.findViewById(R.id.txt_name) as TextView
            textViewName.text = rowInnerData.name

            val imageView = itemView.findViewById(R.id.image) as ImageView
            Glide
                .with(context)
                .load(rowInnerData.img)
                .centerCrop()
                .placeholder(R.drawable.ic_smile)
                .into(imageView)
        }
    }
}