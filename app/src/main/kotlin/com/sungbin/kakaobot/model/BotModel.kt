package com.sungbin.kakaobot.model


/**
 * Created by SungBin on 2020-07-10.
 */

data class BotModel(val name: String, val pinned: Boolean, val power: Boolean, val lastRunTime: String) {
    override fun toString(): String {
        return "$name; lastRunTime: $lastRunTime"
    }
}