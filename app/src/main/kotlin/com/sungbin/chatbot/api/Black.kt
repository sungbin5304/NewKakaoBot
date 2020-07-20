package com.sungbin.chatbot.api

import android.annotation.SuppressLint
import android.content.Context
import com.sungbin.sungbintool.DataUtils.readData
import com.sungbin.sungbintool.DataUtils.saveData

object Black {
    @SuppressLint("StaticFieldLeak")
    private var ctx: Context? = null

    fun init(context: Context?) {
        ctx = context
    }

    fun addSender(sender: String) {
        val preSenderList: String = readData(ctx!!, "SenderBlackList", "")
        val newSenderList = preSenderList + "\n" + sender
        saveData(ctx!!, "SenderBlackList", newSenderList)
    }

    fun removeSender(sender: String) {
        val preSenderList: String = readData(ctx!!, "SenderBlackList", "")
        val newSenderList = preSenderList.replace("\n" + sender, "")
        saveData(ctx!!, "SenderBlackList", newSenderList)
    }

    fun readSender(): String {
        return readData(ctx!!, "SenderBlackList", "")
    }

    fun addRoom(sender: String) {
        val preSenderList: String = readData(ctx!!, "RoomBlackList", "")
        val newSenderList = preSenderList + "\n" + sender
        saveData(ctx!!, "RoomBlackList", newSenderList)
    }

    fun removeRoom(sender: String) {
        val preSenderList: String = readData(ctx!!, "RoomBlackList", "")
        val newSenderList = preSenderList.replace("\n" + sender, "")
        saveData(ctx!!, "RoomBlackList", newSenderList)
    }

    fun readRoom(): String {
        return readData(ctx!!, "RoomBlackList", "")
    }
}