package com.sagrawal.newsapp.utils

import java.io.IOException
import java.nio.charset.Charset

object AppConstant {

    const val COUNTRY = "us"
    const val DEBOUNCE_TIMEOUT = 300L
    const val MIN_SEARCH_CHAR = 3
    const val API_KEY = "9f6482a584804376874b848980b7a044"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val HEADLINES = "Headlines"
    const val SOURCES = "Sources"
    const val COUNTRIES = "Countries"
    const val LANGUAGES = "Languages"
    const val SEARCH = "Search"
    const val NAV_ARG_NEWS_ID = "newsId"
    const val NAV_ARG_COUNTRY = "country"
    const val NAV_ARG_LANGUAGE = "language"
    const val INITIAL_PAGE = 1
    const val PAGE_SIZE = 10

    //WorkManager and Notification
    const val UNIQUE_WORK_NAME = "newsAppPeriodicWork"
    const val MORNING_UPDATE_TIME = 5
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "news_channel"
    const val NOTIFICATION_CHANNEL_NAME = "News"
    const val NOTIFICATION_CONTENT_TITLE = "News"
    const val NOTIFICATION_CONTENT_TEXT = "Check out the latest news ..."

}