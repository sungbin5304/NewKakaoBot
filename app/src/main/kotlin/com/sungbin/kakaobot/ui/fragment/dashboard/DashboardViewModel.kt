package com.sungbin.kakaobot.ui.fragment.dashboard

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sungbin.kakaobot.model.BotModel
import javax.inject.Named

class DashboardViewModel @ViewModelInject constructor(
    @Named("JS") private val jsBotData: ArrayList<BotModel>,
    @Named("SIMPLE") private val simpleBotData: ArrayList<BotModel>,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val jsBot = jsBotData
    val simpleBot = simpleBotData
}