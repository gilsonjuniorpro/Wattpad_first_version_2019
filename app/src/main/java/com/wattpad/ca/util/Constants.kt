package com.wattpad.ca.util


class Constants {

    companion object {
        const val URL_STORIES = "https://www.wattpad.com/api/v3/stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new"

        const val TABLE_STORY = "tb_story"

        const val TABLE_USER = "tb_user"
    }

}