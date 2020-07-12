package com.sungbin.kakaobot.module

import com.sungbin.kakaobot.*
import com.sungbin.kakaobot.model.*
import com.sungbin.kakaobot.utils.bot.*
import com.sungbin.sungbintool.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import java.io.*
import javax.inject.*


/**
 * Created by SungBin on 2020-07-10.
 */

@Module
@InstallIn(ApplicationComponent::class)
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object BotModule {

    private val context = KakaoBot.context

    @Provides
    @Named("JS")
    fun provideJsBot(): ArrayList<BotModel> {
        val array = ArrayList<BotModel>()
        File("${StorageUtils.sdcard}/${BotPathManager.JS}").listFiles()?.map {
            val name = it.name
            val pined = BotPinUtils.getPined(context, name)
            val lastRunTime = BotRunTimeUtils.getTime(context, name)
            val power = BotPowerUtils.getPower(context, name)
            val data = BotModel(name, pined, power, lastRunTime)
            array.add(data)
        }
        return array
    }

    @Provides
    @Named("SIMPLE")
    fun provideSimpleBot(): ArrayList<BotModel> {
        val array = ArrayList<BotModel>()
        File("${StorageUtils.sdcard}/${BotPathManager.SIMPLE}").listFiles()?.map {
            val name = it.name
            val pined = BotPinUtils.getPined(context, name)
            val lastRunTime = BotRunTimeUtils.getTime(context, name)
            val power = BotPowerUtils.getPower(context, name)
            val data = BotModel(name, pined, power, lastRunTime)
            array.add(data)
        }
        return array
    }
}