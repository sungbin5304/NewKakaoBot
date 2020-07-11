package com.sungbin.kakaobot.ui.fragment.dashboard


import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.ui.fragment.BaseFragment
import com.sungbin.sungbintool.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*


@AndroidEntryPoint
class DashboardFragment : BaseFragment(){

    companion object {
        val instance = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        PermissionUtils.request(
            activity as Activity,
            "권한내놔!!!",
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )

        if(viewModel.jsBot.isEmpty() && viewModel.simpleBot.isEmpty()){
            rv_bot.hide(false, true)
            cl_bot_none.show(false)
        }
    }
}