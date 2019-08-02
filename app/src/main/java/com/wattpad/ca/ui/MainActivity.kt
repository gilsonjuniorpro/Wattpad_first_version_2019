package com.wattpad.ca.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wattpad.ca.R
import com.wattpad.ca.adapter.StoryAdapter
import com.wattpad.ca.controller.StoryController
import com.wattpad.ca.dto.StoryDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val objResponse = StoryController.listStories(this) // wait until job is done

        if (objResponse != null && objResponse.isNotEmpty()) {
            setTextOnMainThread(objResponse, null)
        } else {
            setTextOnMainThread(null, "Couldn't get Result")
        }
    }


    private fun setNewText(stories: List<StoryDTO>?, message: String?){
        progress.visibility = View.GONE
        if(!TextUtils.isEmpty(message)) {
            tvEmptyView.text = "empty"
        }else{
            tvEmptyView.text = message
            listStories.adapter = StoryAdapter(stories!!, this)
        }
    }


    private suspend fun setTextOnMainThread(stories: List<StoryDTO>?, message: String?) {
        withContext (Main) {
            setNewText(stories, message)
        }
    }
}