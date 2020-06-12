package com.sungbin.kakaobot.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sungbin.kakaobot.utils.TimeManager

class MainViewModel(context: Context?) : ViewModel() {
    val lastRunningTime = TimeManager.getLastRunningTime(context)
}