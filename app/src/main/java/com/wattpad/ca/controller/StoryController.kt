package com.wattpad.ca.controller

import android.content.Context
import com.wattpad.ca.data.WattpadDatabase
import com.wattpad.ca.dto.ResponseDTO
import com.wattpad.ca.dto.StoryDTO
import com.wattpad.ca.pojo.Story
import com.wattpad.ca.service.StoryService
import com.wattpad.ca.util.Utils

object StoryController {

    private var db: WattpadDatabase? = null

    fun listStories(context: Context): List<StoryDTO>? {
        var list: List<StoryDTO>? = null

        if(Utils.hasConnection(context)){
            var responseDTO = StoryService.listStories()
            if (responseDTO != null && responseDTO.stories!!.isNotEmpty()) {
                saveData(context, responseDTO)
                list = responseDTO.stories
            }
        }else{
            list = loadDataFromDatabase(context)
        }
        return list
    }


    fun saveData(context: Context, responseDTO: ResponseDTO){
        initDatabase(context)

        //first remove all data from db
        db!!.storyDao().deleteAll()
        db!!.userDao().deleteAll()

        responseDTO.stories!!.forEachIndexed { i, value ->
            var story = Utils.convertDtoToEntity(responseDTO.stories!![i]) as Story
            db!!.storyDao().insert(story)
        }
    }


    fun loadDataFromDatabase(context: Context): List<StoryDTO> {
        initDatabase(context)
        var stories = db!!.storyDao().getAllStories()

        var listDTO = ArrayList<StoryDTO>()

        stories!!.forEachIndexed { i, value ->
            var storyDTO = Utils.convertEntityToDTO(stories!![i]) as StoryDTO
            listDTO.add(storyDTO)
        }

        return listDTO
    }


    private fun initDatabase(context: Context) {
        if(db == null) db = WattpadDatabase.getDatabase(context)!!
    }
}