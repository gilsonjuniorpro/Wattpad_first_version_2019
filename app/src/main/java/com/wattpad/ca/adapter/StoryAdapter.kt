package com.wattpad.ca.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wattpad.ca.R
import com.wattpad.ca.dto.StoryDTO
import com.wattpad.ca.ui.DetailStoryActivity
import kotlinx.android.synthetic.main.item_story_list.view.*


class StoryAdapter(items : List<StoryDTO>, mContext: Context) : RecyclerView.Adapter<StoryAdapter.ViewHolder>(){

    private var list:List<StoryDTO> = items
    private var context = mContext

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title

        var name = if(list[position].user!!.fullname != null)
                        list[position].user!!.fullname else list[position].user!!.name

        holder.tvFullname.text = name

        var urlImage = list[position].cover
        if(urlImage != null){
            Glide.with(context)
                .load(urlImage)
                .into(holder.ivCover)
        }
        holder.layBase.setOnClickListener { openDetailStory(list[position]) }
    }

    private fun openDetailStory(storyDTO: StoryDTO) {
        var it = Intent(context, DetailStoryActivity::class.java)
        it.putExtra("storyDTO", storyDTO)
        context.startActivity(it)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_story_list,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle = view.tvTitle!!
        val tvFullname = view.tvFullname!!
        val ivCover = view.ivCover!!
        val layBase = view.layBase!!
    }


}