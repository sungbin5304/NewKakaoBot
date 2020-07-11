package com.sungbin.kakaobot.model

import javax.inject.Inject


/**
 * Created by SungBin on 2020-07-10.
 */

data class PersonModel(val name: String, val age: Int) {
    override fun toString(): String {
        return "name: $name / age: $age"
    }
}