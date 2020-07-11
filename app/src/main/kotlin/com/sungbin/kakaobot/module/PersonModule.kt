package com.sungbin.kakaobot.module

import com.sungbin.kakaobot.model.PersonModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


/**
 * Created by SungBin on 2020-07-10.
 */

@Module
@InstallIn(ApplicationComponent::class)
object PersonModule {
    @Provides
    fun providePerson() = PersonModel("SungBin", 19)
}