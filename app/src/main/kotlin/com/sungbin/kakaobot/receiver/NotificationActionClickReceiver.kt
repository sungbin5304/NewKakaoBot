package com.sungbin.kakaobot.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.utils.bot.BotNotificationUtils
import com.sungbin.sungbintool.DataUtils
import com.sungbin.sungbintool.ToastUtils

/**
 * Created by SungBin on 2020-05-10.
 */

class NotificationActionClickReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.getStringExtra("value")!!){
            "stop" -> {
                DataUtils.saveData(context, "BotOn", "false")
                BotNotificationUtils.create(context)
                ToastUtils.show(
                    context,
                    context.getString(R.string.bot_work_stop),
                    ToastUtils.SHORT,
                    ToastUtils.INFO
                )
            }
            "start" -> {
                DataUtils.saveData(context, "BotOn", "true")
                BotNotificationUtils.create(context)
                ToastUtils.show(
                    context,
                    context.getString(R.string.bot_work_start),
                    ToastUtils.SHORT,
                    ToastUtils.INFO
                )
            }
            "reload" -> {}
            "delete" -> {
                BotNotificationUtils.delete(context)
            }
        }
    }
}