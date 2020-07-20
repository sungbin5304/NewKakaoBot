package com.sungbin.chatbot.api

import android.content.Context

object AppData {
    private var ctx: Context? = null
    fun init(context: Context?) {
        ctx = context
    }

    fun getInt(name: String, _null: Int): Int {
        val sf =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        return sf.getInt(name, _null)
    }

    fun getBoolean(name: String, _null: Boolean): Boolean {
        val sf =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        return sf.getBoolean(name, _null)
    }

    fun getString(name: String, _null: String): String? {
        val sf =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        return sf.getString(name, _null)
    }

    fun putString(name: String, data: String) {
        val sharedPreferences =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putString(name, data)
        editor.apply()
    }

    fun putInt(name: String, data: Int) {
        val sharedPreferences =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putInt(name, data)
        editor.apply()
    }

    fun putBoolean(name: String, data: Boolean) {
        val sharedPreferences =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putBoolean(name, data)
        editor.apply()
    }

    fun remove(name: String) {
        val sharedPreferences =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.remove(name)
        editor.apply()
    }

    fun clear() {
        val sharedPreferences =
            ctx!!.getSharedPreferences(
                "AppData",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}