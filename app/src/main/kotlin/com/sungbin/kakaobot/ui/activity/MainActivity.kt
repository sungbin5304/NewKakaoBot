package com.sungbin.kakaobot.ui.activity

import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.listener.AppBarStateChangeListener
import com.sungbin.kakaobot.model.PersonModel
import com.sungbin.kakaobot.ui.fragment.main.MainFragment
import com.sungbin.kakaobot.utils.TimeManager
import com.sungbin.sungbintool.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var person: PersonModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        LogUtils.d("Activity", person.toString())

        abl_appbar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                when (state!!) {
                    State.COLLAPSED -> { //닫힘
                        cl_toolbar_layout.show()
                    }
                    State.EXPANDED -> { //열림
                        cl_toolbar_layout.hide()
                    }
                    else -> { //중간과정, Useless
                        /*when (lastState) {
                            State.EXPANDED -> { //이미 열렸을 경우 -> 닫음
                                cl_toolbar_layout.show()
                            }
                            State.COLLAPSED -> { //이미 닫혔을 경우 -> 열림
                                cl_toolbar_layout.hide()
                            }
                            else -> {} //Useless
                        }*/
                    }
                }
            }
        })

        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                tv_working_time?.post {
                    tv_working_time.text =
                        TimeManager.getTimeGap(
                            TimeManager.getLastRunningTime(applicationContext) ?: 0L
                        )
                }
            }
        }
        timer.schedule(timerTask, 0, 1000)
    }
}