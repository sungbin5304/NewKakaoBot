package com.sungbin.kakaobot.ui.fragment.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.ui.fragment.BaseFragment
import java.util.*
import kotlin.time.ExperimentalTime


class MainFragment : BaseFragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    private val timer = Timer()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @ExperimentalTime
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(context)
        )[MainViewModel::class.java]
    }
}