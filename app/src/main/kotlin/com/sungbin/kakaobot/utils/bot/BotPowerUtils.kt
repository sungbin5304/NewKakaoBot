package com.sungbin.kakaobot.utils.bot

import android.content.Context
import com.sungbin.sungbintool.DataUtils

object BotPowerUtils {
    fun getPower(context: Context, name: String): Boolean{
        return DataUtils.readData(context, "$name - power", "false").toBoolean()
    }

    fun setPower(context: Context, name: String, isOn: Boolean){
        DataUtils.saveData(context, "$name - power", isOn.toString())
    }
}