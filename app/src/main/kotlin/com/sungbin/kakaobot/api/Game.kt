package com.sungbin.kakaobot.api

import com.github.kimkevin.hangulparser.*
import com.sungbin.kakaobot.api.game.chosung.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by SungBin on 2020-05-14.
 */

object Game {

    fun chosungQuiz(type: Int): ArrayList<Any> {
        val array = ArrayList<Any>()
        val subject = ChosungType.getName(type)
        val words = ChosungData.getData(type).split("\n")
        val index = Random().nextInt(words.size)
        val word = words[index]
        val chosungs = ArrayList<String>()
        for (element in word){
            chosungs.add(HangulParser.disassemble(element)[0])
        }
        array.add(subject)
        array.add(word)
        array.add(chosungs)
        return array
    }

}