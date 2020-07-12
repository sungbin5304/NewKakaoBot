package com.sungbin.kakaobot.api

import android.content.*
import android.os.*

object Device {
    private var ctx: Context? = null
    fun init(context: Context?) {
        ctx = context
    }

    val phoneModel: String
        get() = Build.MODEL

    val androidSDKVersion: Int
        get() = Build.VERSION.SDK_INT

    val androidVersion: String
        get() = Build.VERSION.RELEASE

    val battery: Int
        get() {
            val intentBattery =
                ctx!!.registerReceiver(
                    null,
                    IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                )
            val level = intentBattery!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intentBattery.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = level / scale.toFloat()
            return (batteryPct * 100).toInt()
        }

    val isCharging: Boolean
        get() {
            val intentBattery =
                ctx!!.registerReceiver(
                    null,
                    IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                )
            val status = intentBattery!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            var isCharging = false
            if (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL) {
                isCharging = true
            }
            return isCharging
        }
}