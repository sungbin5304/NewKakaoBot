package com.sungbin.chatbot.module

import com.sungbin.chatbot.ChatBot
import com.sungbin.chatbot.model.BotModel
import com.sungbin.chatbot.utils.BotFixedUtils
import com.sungbin.chatbot.utils.BotPowerUtils
import com.sungbin.chatbot.utils.BotRunTimeUtils
import com.sungbin.chatbot.utils.BotTypeManager.FACEBOOK
import com.sungbin.chatbot.utils.BotTypeManager.KAKAOTALK
import com.sungbin.chatbot.utils.BotTypeManager.LINE
import com.sungbin.chatbot.utils.BotTypeManager.TELEGRAM
import com.sungbin.sungbintool.StorageUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.random.Random


/**
 * Created by SungBin on 2020-07-10.
 */

@Module
@InstallIn(ApplicationComponent::class)
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object BotModule {

    private val context = ChatBot.context
    private val sdcard = StorageUtils.sdcard

    @Provides
    @Singleton
    @Named(KAKAOTALK)
    fun provideKakaoTalk() = getBots(KAKAOTALK)

    @Provides
    @Singleton
    @Named(LINE)
    fun provideLine() = getBots(LINE)

    @Provides
    @Singleton
    @Named(FACEBOOK)
    fun provideFaceBook() = getBots(FACEBOOK)

    @Provides
    @Singleton
    @Named(TELEGRAM)
    fun provideTelegram() = getBots(TELEGRAM)

    @Provides
    @Singleton
    @Named("TEST")
    fun provideTest(): ArrayList<BotModel> {
        val array = ArrayList<BotModel>()
        for(i in 0..25){
            val name = UUID.randomUUID().toString().replace("-", "").substring(0..10)
            val fixed = Random.nextBoolean()
            val lastRunTime = "마지막 작동: 없음"
            val power = Random.nextBoolean()
            val data = BotModel(name, fixed, power, lastRunTime)
            array.add(data)
        }
        return array
    }

    private fun getBots(path: String): ArrayList<BotModel> {
        val array = ArrayList<BotModel>()
        File("$sdcard/$path").listFiles()?.map {
            val name = it.name
            val fixed = BotFixedUtils.getFixed(context, name)
            val lastRunTime = BotRunTimeUtils.getTime(context, name)
            val power = BotPowerUtils.getPower(context, name)
            val data = BotModel(name, fixed, power, lastRunTime)
            array.add(data)
        }
        return array
    }
}