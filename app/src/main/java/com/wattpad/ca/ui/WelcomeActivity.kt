package com.wattpad.ca.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.wattpad.ca.R
import com.wattpad.ca.util.PrefManager
import kotlinx.android.synthetic.main.activity_welcome.view.*

class WelcomeActivity : AppCompatActivity() {

    private var introViewPager: ViewPager? = null
    private var introViewPagerAdapter: IntroScreenViewPagerAdapter? = null
    private var introBullets: Array<TextView>? = null
    private var introBulletsLayout: LinearLayout? = null
    private var introSliderLayouts: IntArray? = null
    private var btSkip: Button? = null
    private var btNext: Button? = null
    private var prefManager: PrefManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val welcomeLayout = layoutInflater.inflate(R.layout.activity_welcome, null)
        setContentView(welcomeLayout)
        prefManager = PrefManager(this)
        if (!prefManager!!.isFirstTimeLaunch) {
            applicationStartup()
            finish()
        }
        introViewPager = welcomeLayout.intro_view_pager
        introBulletsLayout = welcomeLayout.intro_bullets
        btSkip = welcomeLayout.btSkip
        btNext = welcomeLayout.btNext

        //Get the intro slides
        introSliderLayouts = intArrayOf(
            R.layout.welcome_slide1,
            R.layout.welcome_slide2,
            R.layout.welcome_slide3,
            R.layout.welcome_slide4,
            R.layout.welcome_slide5)

        // adding bottom introBullets
        makeIIntroBullets(0)
        introViewPagerAdapter = IntroScreenViewPagerAdapter()
        introViewPager!!.adapter = introViewPagerAdapter
        introViewPager!!.addOnPageChangeListener(introViewPagerListener)
        (btSkip as View?)!!.setOnClickListener { applicationStartup() }
        (btNext as View?)!!.setOnClickListener {
            // checking for last page
            // if last page home screen will be launched
            val current = getItem(+1)
            if (current < introSliderLayouts!!.size) {
                // move to next screen
                introViewPager!!.currentItem = current
            } else {
                applicationStartup()
            }
        }
        // making notification bar transparent
        setTransparentStatusBar()
    }
    private fun makeIIntroBullets(currentPage: Int) {
        var arraySize = introSliderLayouts!!.size
        introBullets = Array(arraySize) { textboxInit() }
        val colorsActive = resources.getIntArray(R.array.array_intro_bullet_active)
        val colorsInactive = resources.getIntArray(R.array.array_intro_bullet_inactive)
        introBulletsLayout!!.removeAllViews()
        for (i in 0 until introBullets!!.size) {
            introBullets!![i] = TextView(this)
            introBullets!![i].text = Html.fromHtml("&#9679;")
            introBullets!![i].textSize = 30F
            introBullets!![i].setTextColor(colorsInactive[currentPage])
            introBulletsLayout!!.addView(introBullets!![i])
        }
        if (introBullets!!.isNotEmpty())
            introBullets!![currentPage].setTextColor(colorsActive[currentPage])
    }


    private fun textboxInit(): TextView {
        return TextView(applicationContext)
    }


    private fun getItem(i: Int): Int {
        return introViewPager!!.currentItem + i
    }


    private fun applicationStartup() {
        prefManager!!.isFirstTimeLaunch = false
        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        finish()
    }


    private var introViewPagerListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            makeIIntroBullets(position)

            /*Based on the page position change the button text*/
            if (position == introSliderLayouts!!.size - 1) {
                btNext!!.text = getString(R.string.done_button_title)
                btSkip!!.visibility = View.GONE
            } else {
                btNext!!.text = getString(R.string.next_button_title)
                btSkip!!.visibility = View.VISIBLE
            }
        }
        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
            //Do nothing for now
        }
        override fun onPageScrollStateChanged(arg0: Int) {
            //Do nothing for now
        }
    }


    private fun setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


    inner class IntroScreenViewPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)
            val view = layoutInflater.inflate(introSliderLayouts!![position], container, false)
            container.addView(view)
            return view
        }
        override fun getCount(): Int {
            return introSliderLayouts!!.size
        }
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}