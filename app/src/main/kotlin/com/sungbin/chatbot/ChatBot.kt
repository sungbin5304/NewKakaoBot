package com.sungbin.chatbot

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by SungBin on 2020-07-11.
 */

@HiltAndroidApp
class ChatBot : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}