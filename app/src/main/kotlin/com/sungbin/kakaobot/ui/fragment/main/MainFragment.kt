package com.sungbin.kakaobot.ui.fragment.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.model.PersonModel
import com.sungbin.kakaobot.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : BaseFragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    private val timer = Timer()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.test.toString().toastS(context)
    }
}