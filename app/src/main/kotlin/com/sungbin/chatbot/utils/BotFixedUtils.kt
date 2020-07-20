package com.sungbin.chatbot.utils

import android.content.Context
import com.sungbin.sungbintool.DataUtils


/**
 * Created by SungBin on 2020-07-12.
 */

object BotFixedUtils {

    fun getFixed(context: Context, name: String): Boolean {
        return DataUtils.readData(context, "$name - pined", "false").toBoolean()
    }

    fun setFixed(context: Context, name: String, isPined: Boolean) {
        DataUtils.saveData(context, "$name - pined", isPined.toString())
    }

}