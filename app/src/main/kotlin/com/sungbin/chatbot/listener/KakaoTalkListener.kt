package com.sungbin.chatbot.listener

import android.app.Notification
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.Spanned
import androidx.annotation.RequiresApi
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.text.HtmlCompat
import com.sungbin.chatbot.api.Api
import com.sungbin.chatbot.api.AppData
import com.sungbin.chatbot.api.Black
import com.sungbin.chatbot.api.Device
import com.sungbin.chatbot.api.Utils
import com.sungbin.chatbot.utils.StackUtils.sessions
import com.sungbin.kakaobot.R
import com.sungbin.sungbintool.DataUtils
import com.sungbin.sungbintool.ToastUtils
import java.util.*
import kotlin.collections.set

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING", "DEPRECATION", "UNUSED_VARIABLE")
class KakaoTalkListener : NotificationListenerService() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        )
        ToastUtils.show(
            context,
            getString(R.string.power_on),
            ToastUtils.SHORT,
            ToastUtils.INFO
        )

        AppData.init(context)
        Api.init(context)
        Device.init(context)
        Utils.init(context)
        Black.init(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastUtils.show(
            context, getString(R.string.power_off),
            ToastUtils.SHORT, ToastUtils.INFO
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        /*if (!DataUtils.readData(applicationContext, "BotPower", "false").toBoolean()) return
        var packages = DataUtils.readData(ctx!!, "packages", "com.kakao.talk").trim()
        if (packages.isBlank()) packages = "com.kakao.talk"
        if (packages.split("\n").contains(sbn.packageName)) {*/
        if (true) {
            val wExt =
                Notification.WearableExtender(sbn.notification)
            for (act in wExt.actions) {
                if (act.remoteInputs != null && act.remoteInputs.isNotEmpty()) {
                    if (act.title.toString().toLowerCase(Locale.getDefault()).contains("reply")
                        || act.title.toString()
                            .toLowerCase(Locale.getDefault()).contains("답장")
                    ) {
                        val extras = sbn.notification.extras
                        var room: String?
                        var sender: String?
                        var msg: String?
                        var isGroupChat = false
                        val packageName = sbn.packageName

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            room = extras.getString("android.summaryText")
                            sender = extras.get("android.title")?.toString()
                            msg = extras.get("android.text")?.toString()
                            if (room == null) {
                                room = sender
                                isGroupChat = false
                            } else isGroupChat = true
                        } else {
                            var kakaotalkVersion = 0L
                            var noKakaoTalk = false
                            try {
                                kakaotalkVersion =
                                    PackageInfoCompat.getLongVersionCode(
                                        packageManager.getPackageInfo("com.kakao.talk", 0)
                                    )
                            } catch (ignored: Exception) {
                                noKakaoTalk = true
                            }

                            if (noKakaoTalk || packageName != "com.kakao.talk"
                                || kakaotalkVersion < 1907310
                            ) {
                                room = extras.getString("android.title")
                                if (extras.get("android.text") !is String) {
                                    val html = HtmlCompat.toHtml(
                                        extras.get("android.text")
                                                as Spanned,
                                        HtmlCompat.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
                                    )
                                    sender = HtmlCompat.fromHtml(
                                        html.split("<b>")[1].split("</b>")[0],
                                        HtmlCompat.FROM_HTML_MODE_COMPACT
                                    ).toString()
                                    msg = HtmlCompat.fromHtml(
                                        html.split("</b>")[1].split("</p>")[0]
                                            .substring(1), HtmlCompat.FROM_HTML_MODE_COMPACT
                                    ).toString()
                                } else {
                                    sender = room
                                    msg = extras.get("android.text")?.toString()
                                }
                            } else {
                                room = extras.getString("android.subText")
                                sender = extras.getString("android.title")
                                msg = extras.getString("android.text")
                                isGroupChat = room != null
                                if (room == null) room = sender
                            }
                        }

                        if (!sessions.containsKey(room)) sessions[room ?: sender ?: ""] = act
                        val blackRoom = DataUtils.readData(
                            context, "RoomBlackList", ""
                        )
                        val blackSender = DataUtils.readData(
                            context, "SenderBlackList", ""
                        )
                        if (!blackRoom.contains(room!!) ||
                            !blackSender.contains(sender!!)
                        ) {
                            com.sungbin.sungbintool.LogUtils.d(
                                arrayOf(
                                    sender,
                                    msg,
                                    room,
                                    isGroupChat,
                                    packageName
                                )
                            )
                            /*chatHook(
                                sender!!, msg!!, room, isGroupChat, act,
                                ((sbn.notification.getLargeIcon())
                                    .loadDrawable(ctx) as BitmapDrawable).bitmap,
                                sbn.packageName,
                                ctx!!
                            )*/
                        }
                    }
                }
            }
        }
    }

    fun reply(session: Notification.Action, value: String) {
        val sendIntent = Intent()
        val msg = Bundle()
        for (inputable in session.remoteInputs) msg.putCharSequence(
            inputable.resultKey,
            value
        )
        RemoteInput.addResultsToIntent(session.remoteInputs, sendIntent, msg)
        try {
            session.actionIntent.send(context, 0, sendIntent)
        } catch (e: Exception) {
            ToastUtils.show(
                context, e.toString(),
                ToastUtils.LONG, ToastUtils.ERROR
            )
        }
    }
}