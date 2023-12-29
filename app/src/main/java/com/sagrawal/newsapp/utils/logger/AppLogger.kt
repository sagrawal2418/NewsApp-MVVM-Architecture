package me.amitshekhar.newsapp.utils.logger

import android.util.Log
import com.sagrawal.newsapp.utils.logger.Logger

class AppLogger : Logger {

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

}