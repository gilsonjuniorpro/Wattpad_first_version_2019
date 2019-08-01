package com.wattpad.ca.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.wattpad.ca.dto.StoryDTO
import com.wattpad.ca.dto.UserDTO
import com.wattpad.ca.pojo.Story
import com.wattpad.ca.pojo.User

object Utils {

    fun convertDtoToEntity(dto: Any): Any? {
        return if (dto is StoryDTO) {
            // this part was inserted because kotlin didn't recognize the inference
            var storyDTO = dto as StoryDTO

            var id = storyDTO.id
            var title = storyDTO.title
            var userDTO = storyDTO.user
            var cover = storyDTO.cover
            var rating = storyDTO.rating
            var voteCount = storyDTO.voteCount
            var description = storyDTO.description

            var user = User(userDTO?.name!!, userDTO?.avatar!!, userDTO?.fullname!!)

            Story(id!!, title!!, user!!, cover!!, rating!!, voteCount!!, description!!)
        }else{
            return null
        }
    }


    fun hasConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }

    fun convertEntityToDTO(entity: Any): Any? {
        return if (entity is Story) {
            // this part was inserted because kotlin didn't recognize the inference
            var story = entity as Story

            var id = story.id
            var title = story.title
            var user = story.user
            var cover = story.cover
            var rating = story.rating
            var voteCount = story.voteCount
            var description = story.description

            var userDTO = UserDTO(user?.name!!, user?.avatar!!, user?.fullname!!)

            StoryDTO(id!!, title!!, userDTO!!, cover!!, rating!!, voteCount!!, description!!, null)
        }else{
            return null
        }
    }
}