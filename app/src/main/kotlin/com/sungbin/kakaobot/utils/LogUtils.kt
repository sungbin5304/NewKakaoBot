package com.sungbin.kakaobot.utils

import com.sungbin.kakaobot.utils.bot.BotPathManager
import com.sungbin.sungbintool.StorageUtils

object LogUtils {
    object Type {
        const val INFO = 0
        const val SUCCESS = 1
        const val ERROR = 2
        const val DEBUG = 3
    }

    private fun getPath(name: String, type: String): String{
        StorageUtils.createFolder("${BotPathManager.LOG}/$name")
        return "${BotPathManager.LOG}/$name/$type.log"
    }

    fun save(name: String, content: String, time: String, type: Int){
        var path = getPath(name, "content")
        StorageUtils.save(path, content)

        path = getPath(name, "time")
        StorageUtils.save(path, time)

        path = getPath(name, "type")
        StorageUtils.save(path, type.toString())
    }

    fun get(name: String, type: String): String{
        return StorageUtils.read(
            getPath(name, type), ""
        ).toString()
    }

    fun remove(name: String, type: String){
        StorageUtils.delete(getPath(name, type))
    }

}