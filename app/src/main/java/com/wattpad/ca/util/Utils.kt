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
            var id = dto.id
            var title = dto.title
            var userDTO = dto.user
            var cover = dto.cover
            var rating = dto.rating
            var voteCount = dto.voteCount
            var description = dto.description

            var tags: String? = dto?.tags.toString()

            var user = User(userDTO?.name!!, userDTO?.avatar!!, userDTO?.fullname!!)

            Story(id!!, title!!, user!!, cover!!, rating!!, voteCount!!, description!!, tags!!)
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
            var id = entity.id
            var title = entity.title
            var user = entity.user
            var cover = entity.cover
            var rating = entity.rating
            var voteCount = entity.voteCount
            var description = entity.description

            var listTags = mutableListOf<String>()

            if(entity.tags != null){
                var list = entity.tags.removePrefix("[").removeSuffix("]")
                var result: List<String> = list.split(",").map { it.trim() }
                result.forEach { listTags.add(it) }
            }

            var userDTO = UserDTO(user?.name!!, user?.avatar!!, user?.fullname!!)

            StoryDTO(id!!, title!!, userDTO!!, cover!!, rating!!, voteCount!!, description!!, listTags!!)
        }else{
            return null
        }
    }
}