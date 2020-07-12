@file:Suppress("DEPRECATION")

package com.sungbin.kakaobot.api

import android.content.Context
import android.os.*
import android.text.*
import com.sungbin.kakaobot.listener.*
import com.sungbin.kakaobot.listener.KakaoTalkListener.Companion.showAll
import com.sungbin.kakaobot.utils.*
import com.sungbin.sungbintool.*
import org.jsoup.*
import org.mozilla.javascript.*
import java.io.*
import java.net.*

object Api {
    private var ctx: Context? = null
    private const val USER_AGENT =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"

    fun init(context: Context?) {
        ctx = context
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getHtmlFromJava(address: String): String? {
        try {
            val url = URL(address)
            val con = url.openConnection()
            if (con != null) {
                con.connectTimeout = DataUtils.readData(ctx!!, "HtmlLimitTime", "5").toInt() * 1000
                con.useCaches = false
                val isr = InputStreamReader(con.getInputStream())
                val br = BufferedReader(isr)
                var str = br.readLine()
                var line: String? = ""
                while ({ line = br.readLine(); line }() != null) {
                    str += "\n" + line
                }
                br.close()
                isr.close()
                return str
            }
            return null
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getHtmlFromJsoup(address: String): String {
        return Jsoup
            .connect(address)
            .timeout(DataUtils.readData(ctx!!, "HtmlLimitTime", "5").toInt() * 1000)
            .get()
            .toString()
    }

    fun replyRoom(room: String, msg: String): Boolean {
        return if (StackUtils.sessions.containsKey(room)) {
            KakaoTalkListener.reply(StackUtils.sessions[room]!!, msg)
            true
        } else false
    }

    fun replyRoomShowAll(
        room: String,
        msg1: String,
        msg2: String
    ): Boolean {
        return if (StackUtils.sessions.containsKey(room)) {
            KakaoTalkListener.reply(
                StackUtils.sessions[room]!!,
                msg1 + showAll + msg2
            )
            true
        } else false
    }

    fun deleteHtml(html: String): String {
        return Html.fromHtml(html).toString()
    }

    fun post(
        address: String,
        postName: NativeArray,
        postData: NativeArray
    ): String {
        if (postName.size != postData.size) return "postName과 postData의 크기가 같아야 합니다!"
        else {
            Thread {
                val jsoup = Jsoup.connect(address)
                for (i in 0 .. postName.size) {
                    jsoup.data(postName[i].toString(), postData[i].toString())
                }
                jsoup.post()
            }.start()
        }
        return "true"
    }
}