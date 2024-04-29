package com.sagrawal.nativelib

import android.content.Context

object Provider {

    init {
        System.loadLibrary("newsapp")
    }

    external fun getApiKey(debugMode: Boolean, context: Context): String
    external fun getBaseUrl(debugMode: Boolean, context: Context): String
}