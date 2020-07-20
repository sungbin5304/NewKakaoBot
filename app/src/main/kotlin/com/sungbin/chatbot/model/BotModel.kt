package com.sungbin.chatbot.model


/**
 * Created by SungBin on 2020-07-10.
 */

data class BotModel(
    val name: String,
    val fixed: Boolean,
    val power: Boolean,
    val lastRunTime: String
) {
    override fun toString(): String {
        return "$name; lastRunTime: $lastRunTime"
    }
}