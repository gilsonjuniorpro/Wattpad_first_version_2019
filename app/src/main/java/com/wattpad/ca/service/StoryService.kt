package com.wattpad.ca.service

import com.google.gson.Gson
import com.wattpad.ca.dto.ResponseDTO
import com.wattpad.ca.util.Constants
import java.net.URL

object StoryService {

    fun listStories(): ResponseDTO? {
        val json = URL(Constants.URL_STORIES).readText()
        return Gson().fromJson(json, ResponseDTO::class.java)
    }

}