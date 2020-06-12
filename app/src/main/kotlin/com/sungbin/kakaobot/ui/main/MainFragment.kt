package com.sungbin.kakaobot.ui.main


import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.appbar.AppBarLayout
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.listener.AppBarStateChangeListener
import com.sungbin.kakaobot.ui.BaseFragment
import com.sungbin.kakaobot.utils.TimeManager
import com.sungbin.sungbintool.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.seconds


class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val timer = Timer()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    @ExperimentalTime
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(context)
        )[MainViewModel::class.java]

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

        val timerTask = object : TimerTask() {
            override fun run() {
                tv_working_time?.post {
                    tv_working_time.text =
                        TimeManager.getTimeGap(viewModel.lastRunningTime!!)
                }
            }
        }
        timer.schedule(timerTask, 0, 1000)
    }

    private fun View.show() {
        YoYo
            .with(Techniques.FadeIn)
            .withListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                    this@show.visibility = View.VISIBLE
                }

            })
            .duration(500)
            .playOn(this)
    }

    private fun View.hide() {
        YoYo
            .with(Techniques.FadeOut)
            .withListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                    this@hide.visibility = View.GONE
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                }

            })
            .duration(500)
            .playOn(this)
    }
}