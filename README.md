# Wattpad

This App was made as part of the Wattpad test for an Android Developer Opportunity.

The objective of this App is to use the Wattpad's API and get information using REST.

## How is this App
# Images
<img src="http://www.projectconnect.com.br/github_imagens/Screenshot_20190801-200203.png" width="15%"></img>
<img src="http://www.projectconnect.com.br/github_imagens/Screenshot_20190801-200207.png" width="15%"></img>
<img src="http://www.projectconnect.com.br/github_imagens/Screenshot_20190801-200210.png" width="15%"></img>
<img src="http://www.projectconnect.com.br/github_imagens/Screenshot_20190801-200213.png" width="15%"></img>
<img src="http://www.projectconnect.com.br/github_imagens/Screenshot_20190801-200217.png" width="15%"></img>

-------------
# Video
[![Watch the video](http://www.projectconnect.com.br/github_imagens/capa_wattpad.png)](https://youtu.be/zktdmxBlEX4)

-------------

## Libraries

Added this library to make calls to the API. I know could use Retrofit but I decided for this library to training my skills 
* [Coroutines ](https://kotlinlang.org/docs/reference/coroutines-overview.html)
```
    def coroutines_version = "1.2.1"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
```

Added this library to map my objects according to the json response from the API
* [Gson](https://github.com/google/gson) - Gson is a Java library - implementation 'com.google.code.gson:gson:2.8.5'

Added this library to make calls to the API and set the value direct to the ImageView without using a task. Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* [Glide](https://github.com/bumptech/glide) - implementation 'com.github.bumptech.glide:glide:4.3.1'
    
Added this library to store the information and provide offline content. Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
* [Room](https://developer.android.com/training/data-storage/room)

```
    implementation 'androidx.room:room-runtime:2.0.0'
    kapt 'androidx.room:room-compiler:2.0.0'
```

## Structure
-structure created<br />
-POJOs mapped<br />
-all files created<br />

## Codification
I chose for this App to use IntroSlider that allows me to show some information in the first use of the App. At any time that the App is opened it makes a call to the server and gets new content, this content is stored and kept until the next call and allows the user to use the App offline.

I know I can use the Retrofit library to make calls to the API but for this project I chose to use Coroutines, like this:
```
    CoroutineScope(IO).launch {
        callApiRequest()
    }
```

I get the response from the server in JSON format and use GSON to convert the response to the mapped Object, like this:
```
fun listStories(): ResponseDTO? {
    val json = URL(Constants.URL_STORIES).readText()
    return Gson().fromJson(json, ResponseDTO::class.java)
}
```

After getting the object mapped the adapter is populated with the information provided for the API, if the user clicks on any item from the list a new Activity will be loaded and the object Story will be sent to this new Activity as a parameter.
```
    private fun openDetailStory(storyDTO: StoryDTO) {
        var it = Intent(context, DetailStoryActivity::class.java)
        it.putExtra("storyDTO", storyDTO)
        context.startActivity(it)
    }
```

In the DetailStoryActivity I get the Object StoryDTO as parameter from the intent, just remembering to pass an object as parameter you need to Serialize it, I chose to use the annotation @Parcelize to implement Parcelable from kotlin. A Parcelable is the Android implementation of the Java Serializable. It assumes a certain structure and way of processing it. This way a Parcelable can be processed relatively fast, compared to the standard Java serialization.
```
    var storyDTO: StoryDTO = intent.getParcelableExtra("storyDTO")
```

