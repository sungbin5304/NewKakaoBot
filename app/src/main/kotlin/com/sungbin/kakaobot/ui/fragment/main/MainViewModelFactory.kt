package com.sungbin.kakaobot.ui.fragment.main


/**
 * Created by SungBin on 2020-06-12.
 */


class MainViewModelFactory/*(private val context: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(context) as T
    }
}*/