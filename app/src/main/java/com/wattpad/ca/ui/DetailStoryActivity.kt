package com.wattpad.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.wattpad.ca.R
import com.wattpad.ca.dto.StoryDTO
import kotlinx.android.synthetic.main.activity_detail_story.*

class DetailStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

        var storyDTO: StoryDTO = intent.getParcelableExtra("storyDTO")

        tvTitle.text = storyDTO.title
        tvUser.text = storyDTO.user!!.fullname

        Glide.with(this)
            .load(storyDTO.cover)
            .into(ivCover)

        ivCover.bringToFront()
    }
}
