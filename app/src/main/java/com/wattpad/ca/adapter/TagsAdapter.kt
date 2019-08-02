package com.wattpad.ca.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wattpad.ca.R
import kotlinx.android.synthetic.main.item_tags_list.view.*

class TagsAdapter(items : List<String>, mContext: Context) : RecyclerView.Adapter<TagsAdapter.ViewHolder>(){

    private var list:List<String> = items
    private var context = mContext

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTag.text = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_tags_list,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTag = view.tvTag!!
    }
}