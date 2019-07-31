package com.wattpad.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wattpad.ca.R
import com.wattpad.ca.adapter.StoryAdapter
import com.wattpad.ca.data.WattpadDatabase
import com.wattpad.ca.dto.ResponseDTO
import com.wattpad.ca.dto.StoryDTO
import com.wattpad.ca.pojo.Story
import com.wattpad.ca.pojo.User
import com.wattpad.ca.service.StoryService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var db: WattpadDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = WattpadDatabase.getDatabase(this)!!

        CoroutineScope(IO).launch {
            callApiRequest()
        }

        var mLayoutManager = LinearLayoutManager(this)
        var mDividerItemDecoration = DividerItemDecoration(
            listStories.context, mLayoutManager.orientation)
        listStories.layoutManager = mLayoutManager
        listStories.addItemDecoration(mDividerItemDecoration)
    }


    private suspend fun callApiRequest() {
        val objResponse = StoryService.listStories() // wait until job is done

        if (objResponse != null && objResponse.stories!!.isNotEmpty()) {
            var id = objResponse!!.stories!![0].id
            var title = objResponse!!.stories!![0].title
            var userDTO = objResponse!!.stories!![0].user
            var cover = objResponse!!.stories!![0].cover


            var user = User(userDTO?.name!!, userDTO?.avatar!!, userDTO?.fullname!!)

            var dto = Story(id!!, title!!, user!!, cover!!)

            db!!.storyDao().insert(dto)
            var all = db!!.storyDao().getAllStories()
            setTextOnMainThread(objResponse, null)
        } else {
            setTextOnMainThread(null, "Couldn't get Result")
        }
    }


    private fun setNewText(objResponse: ResponseDTO?, message: String?){
        progress.visibility = View.GONE
        if(!TextUtils.isEmpty(message)) {
            tvResult.text = objResponse?.nextUrl
        }else{
            tvResult.text = message
            var list = objResponse!!.stories as MutableList<StoryDTO>?
            listStories.adapter = StoryAdapter(list!!, this)
        }
    }


    private suspend fun setTextOnMainThread(objResponse: ResponseDTO?, message: String?) {
        withContext (Main) {
            setNewText(objResponse, message)
        }
    }
}