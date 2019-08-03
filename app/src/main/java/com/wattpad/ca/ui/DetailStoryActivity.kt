package com.wattpad.ca.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wattpad.ca.R
import com.wattpad.ca.adapter.TagsAdapter
import com.wattpad.ca.dto.StoryDTO
import kotlinx.android.synthetic.main.activity_detail_story.*

class DetailStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

        var storyDTO: StoryDTO = intent.getParcelableExtra("storyDTO")

        if(storyDTO != null) {
            tvTitle.text = storyDTO.title

            if(storyDTO.user != null) {
                tvUserName.text = storyDTO.user!!.name
                tvUserFullname.text = storyDTO.user!!.fullname

                if(storyDTO.user!!.avatar != null) {
                    Glide.with(this)
                        .load(storyDTO.user!!.avatar)
                        .circleCrop()
                        .into(ivUserAvatar)
                }
            }

            if(storyDTO.cover != null) {
                Glide.with(this)
                    .load(storyDTO.cover)
                    .into(ivCover)
            }

            ivCover.bringToFront()

            tvRating.text = storyDTO.rating.toString()
            tvVoteCount.text = storyDTO.voteCount.toString()
            tvDescription.text = storyDTO.description

            listTags.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
            listTags.adapter = TagsAdapter(storyDTO.tags!!, this)
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        var it = Intent(baseContext, MainActivity::class.java)
        startActivity(it)
        finish()
    }
}