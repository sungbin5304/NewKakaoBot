package com.sungbin.kakaobot.ui.fragment.dashboard

import androidx.hilt.*
import androidx.hilt.lifecycle.*
import androidx.lifecycle.*
import com.sungbin.kakaobot.model.*
import javax.inject.*

class DashboardViewModel @ViewModelInject constructor(
    @Named("JS") private val jsBotData: ArrayList<BotModel>,
    @Named("SIMPLE") private val simpleBotData: ArrayList<BotModel>,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val jsBot = jsBotData
    val simpleBot = simpleBotData
}