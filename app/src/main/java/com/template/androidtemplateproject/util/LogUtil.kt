package com.template.androidtemplateproject.util

import android.util.Log
import com.template.androidtemplateproject.BuildConfig

class LogUtil {
    companion object{
        fun print(key: String = "", logText: String = "") {
            var key = key
            if (!BuildConfig.DEBUG) return
            if (key.length > 20) key = key.substring(0, 20)

            Log.d(key, logText)
        }

        fun print(logText: String = "") {
            if (!BuildConfig.DEBUG) return

            Log.d("AppLogger", logText);
        }
    }
}