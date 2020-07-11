package com.sungbin.kakaobot.utils

import android.app.Notification
import org.mozilla.javascript.Function
import org.mozilla.javascript.ScriptableObject
import java.util.*

object StackUtils {
    val sessions = HashMap<String, Notification.Action>()
    val jsScripts = HashMap<String, Function>()
    val jsScopes = HashMap<String, ScriptableObject>()
}