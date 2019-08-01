package com.wattpad.ca.util

import android.content.Context
import android.content.SharedPreferences

class PrefManager(internal var context: Context) {
    private var introPreference: SharedPreferences
    private var editor: SharedPreferences.Editor
    // shared pref mode
    private var PREF_FILE_NAME = "com.wattpad.ca.welcome_preferences"
    private var IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    private var SHARED_PREF_PRIVATE_MODE = 0
    var isFirstTimeLaunch: Boolean
        get() = introPreference.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }
    init {
        introPreference = context.getSharedPreferences(PREF_FILE_NAME, SHARED_PREF_PRIVATE_MODE)
        editor = introPreference.edit()
    }

}