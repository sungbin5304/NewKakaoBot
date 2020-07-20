package com.sungbin.chatbot.utils

import android.content.Context
import com.sungbin.sungbintool.DataUtils
import java.text.SimpleDateFormat
import java.util.*

object BotRunTimeUtils {
    private fun getTime(): String {
        val format = SimpleDateFormat("a h:m", Locale.KOREA)
        return format.format(Date())
    }

    fun setTime(context: Context, name: String) {
        DataUtils.saveData(
            context, "$name - runtime",
            getTime()
        )
    }

    fun getTime(context: Context, name: String): String {
        return DataUtils.readData(context, "$name - runtime", "마지막 작동: 없음")
    }
}