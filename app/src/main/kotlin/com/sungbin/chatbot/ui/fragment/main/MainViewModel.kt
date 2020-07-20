package com.sungbin.chatbot.ui.fragment.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sungbin.chatbot.model.BotModel
import com.sungbin.chatbot.utils.BotTypeManager.FACEBOOK
import com.sungbin.chatbot.utils.BotTypeManager.KAKAOTALK
import com.sungbin.chatbot.utils.BotTypeManager.LINE
import com.sungbin.chatbot.utils.BotTypeManager.TELEGRAM
import javax.inject.Named

class MainViewModel @ViewModelInject constructor(
    @Named(KAKAOTALK) private val kakaoBotData: ArrayList<BotModel>,
    @Named(FACEBOOK) private val fmBotData: ArrayList<BotModel>,
    @Named(LINE) private val lineBotData: ArrayList<BotModel>,
    @Named(TELEGRAM) private val telegramBotData: ArrayList<BotModel>,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val kakaoBot = kakaoBotData
    val fmBot = fmBotData
    val lineBot = lineBotData
    val telegramBot = telegramBotData
}