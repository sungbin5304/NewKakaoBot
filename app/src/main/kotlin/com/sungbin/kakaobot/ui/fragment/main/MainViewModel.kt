package com.sungbin.kakaobot.ui.fragment.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sungbin.kakaobot.model.PersonModel
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    private val personData: PersonModel,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val person = personData
}