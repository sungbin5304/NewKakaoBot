package com.sungbin.kakaobot.api

import android.content.*
import android.os.*
import android.widget.*
import com.sungbin.kakaobot.R
import com.sungbin.sungbintool.*

@Suppress("DEPRECATION")
object Utils {
    private var ctx: Context? = null
    private var vibrator: Vibrator? = null
    fun init(context: Context?) {
        ctx = context!!
        vibrator =
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    fun makeToast(content: String) {
        Toast.makeText(
            ctx,
            content,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun makeNoti(title: String, content: String) {
        NotificationUtils.setGroupName(ctx!!.getString(R.string.app_name))
        NotificationUtils.createChannel(
            ctx!!,
            ctx!!.getString(R.string.app_name), ctx!!.getString(R.string.app_name))
        NotificationUtils.showNormalNotification(
            ctx!!,
            1, title, content, R.mipmap.ic_launcher)
    }

    fun makeVibration(time: Int) {
        vibrator!!.vibrate(time * 1000.toLong())
    }

    fun copy(content: String) {
        val clipboardManager =
            ctx!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label", content)
        clipboardManager.setPrimaryClip(clipData)
        ToastUtils.show(
            ctx!!, ctx!!.getString(R.string.copied_clipboard),
            ToastUtils.SHORT, ToastUtils.SUCCESS)
    }
}