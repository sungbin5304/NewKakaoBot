package com.sungbin.kakaobot.ui.fragment.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by SungBin on 2020-06-12.
 */


class MainViewModelFactory(private val context: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(context) as T
    }
}