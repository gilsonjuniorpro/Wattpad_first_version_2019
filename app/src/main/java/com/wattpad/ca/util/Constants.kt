package com.wattpad.ca.util


class Constants {

    companion object {
        const val URL_BASE = "https://www.wattpad.com/api/v3/stories"

        const val URL_FIELDS = "&fields=stories(id,title,cover,user,description,rating,voteCount,tags)"

        const val URL_STORIES = "$URL_BASE?offset=0&limit=10$URL_FIELDS&filter=new"

        const val TABLE_STORY = "tb_story"

        const val TABLE_USER = "tb_user"
    }

}