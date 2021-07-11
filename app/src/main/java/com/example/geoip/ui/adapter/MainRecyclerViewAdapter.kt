package com.example.geoip.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.geoip.data.database.GeoInfoEntity
import com.example.geoip.ui.layout.items.RecyclerItem

class MainRecyclerViewAdapter :
    ListAdapter<GeoIpInfoItem, MainRecyclerViewAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val layout: RecyclerItem) : RecyclerView.ViewHolder(layout.container) {
        fun bind(geoIpInfoItem: GeoIpInfoItem){
            layout.keyTextView.text = geoIpInfoItem.key
            layout.valueTextView.text = geoIpInfoItem.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = RecyclerItem(parent.context).build()
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<GeoIpInfoItem>() {
    override fun areItemsTheSame(oldItem: GeoIpInfoItem, newItem: GeoIpInfoItem): Boolean =
        newItem == oldItem

    override fun areContentsTheSame(oldItem: GeoIpInfoItem, newItem: GeoIpInfoItem): Boolean =
        newItem == oldItem
}