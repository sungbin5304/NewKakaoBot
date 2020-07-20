package com.sungbin.chatbot.utils

object BotTypeManager {
    const val KAKAOTALK = "KakaoTalk"
    const val FACEBOOK = "FaceBook-Messenger"
    const val LINE = "Line"
    const val TELEGRAM = "Telegram"

    fun getPath(name: String) =  "Android/data/com.sungbin.chatbot/bots/$name"
}