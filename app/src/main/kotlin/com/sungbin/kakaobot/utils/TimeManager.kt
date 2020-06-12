package com.sungbin.kakaobot.utils

import android.content.Context
import com.sungbin.sungbintool.DataUtils
import com.sungbin.sungbintool.LogUtils
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.floor

object TimeManager {
    private const val BotLastRunningTimeTag = "LastBotRunningTime"

    private const val minuteSecond = 1000 * 60
    private const val hourSecond = minuteSecond * 60

    val nowTimeOffset: Long
        get() = Calendar.getInstance().timeInMillis

    fun hourToSecond(hour: Int) = hour * hourSecond
    fun minuteToSecond(minute: Int) = minute * minuteSecond
    fun getLastRunningTime(context: Context?): Long? {
        return context?.run {
            val data = DataUtils.readData(context, BotLastRunningTimeTag, "0").toLong()
            return if(data != 0L) data else {
                saveLastRunningTime(context)
                nowTimeOffset
            }
        } ?: run {
            LogUtils.e("getLastRunningTime Context is null.")
            null
        }
    }
    fun saveLastRunningTime(context: Context?): Boolean? {
        return context?.run {
            DataUtils.saveData(context, BotLastRunningTimeTag, nowTimeOffset.toString())
            true
        } ?: run {
            LogUtils.e("getLastRunningTime Context is null.")
            null
        }
    }
    fun getTimeGap(millis: Long): String {
        val date2 = Calendar.getInstance()
        val differentMs = Date(date2.timeInMillis).time - Date(millis).time

        val sec = floor((differentMs / 1000).toDouble()) % 60
        val min = floor((differentMs / 60000).toDouble()) % 60
        val hour = floor((differentMs / 3600000).toDouble()) % 60

        return "${hour}시간 ${min}분 ${sec}초".replace(".0", "")
    }
}
