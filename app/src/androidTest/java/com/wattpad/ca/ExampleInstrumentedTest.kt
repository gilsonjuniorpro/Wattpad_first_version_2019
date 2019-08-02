package com.wattpad.ca

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wattpad.ca.controller.StoryController
import com.wattpad.ca.dto.StoryDTO
import com.wattpad.ca.ui.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.wattpad.ca", appContext.packageName)
    }


    @Test
    fun verifyIfListStoriesLoads(){
        var listStories: List<StoryDTO>? = StoryController.listStories(appContext)
        //assertEquals(listStories!!.size, 10)
        assertNotSame(listStories!!.size, 0)
    }

    @Test
    fun verifyIfListStoriesLoadsFromCache(){
        var listStories: List<StoryDTO>? = StoryController.listStories(appContext)
        //assertEquals(listStories!!.size, 10)
        assertNotSame(listStories!!.size, 0)
    }

    @Test
    fun simulateClickOnItemList(){
        Espresso.onView(ViewMatchers.withId(R.id.layBase)).perform(ViewActions.click())
    }


}
