package com.sungbin.chatbot.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.google.android.material.appbar.AppBarLayout
import com.sungbin.chatbot.listener.AppBarStateChangeListener
import com.sungbin.chatbot.ui.fragment.main.MainFragment
import com.sungbin.chatbot.utils.BotNotificationUtils
import com.sungbin.chatbot.utils.BotTypeManager.FACEBOOK
import com.sungbin.chatbot.utils.BotTypeManager.KAKAOTALK
import com.sungbin.chatbot.utils.BotTypeManager.LINE
import com.sungbin.chatbot.utils.BotTypeManager.TELEGRAM
import com.sungbin.chatbot.utils.BotTypeManager.getPath
import com.sungbin.chatbot.utils.TimeManager
import com.sungbin.kakaobot.R
import com.sungbin.sungbintool.DataUtils
import com.sungbin.sungbintool.PermissionUtils
import com.sungbin.sungbintool.StorageUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var isOpened = false

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionUtils.request(
            this,
            "권한내놔!!!",
            arrayOf(
                Manifest.permission.VIBRATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
        //PermissionUtils.requestReadNotification(this)
        StorageUtils.createFolder(getPath(KAKAOTALK))
        StorageUtils.createFolder(getPath(LINE))
        StorageUtils.createFolder(getPath(FACEBOOK))
        StorageUtils.createFolder(getPath(TELEGRAM))

        DataUtils.readData(applicationContext, "BotPower", "false").toBoolean().also {
            sw_bot_power_toolbar.isChecked = it
            sw_bot_power_collapsing.isChecked = it
            if (it) BotNotificationUtils.create(applicationContext)
        }

        BotNotificationUtils.createChannel(applicationContext)

        sw_bot_power_toolbar.setOnCheckedChangeListener { _, isChecked ->
            DataUtils.saveData(applicationContext, "BotPower", isChecked.toString())
            sw_bot_power_collapsing.isChecked = isChecked
            if (isChecked) {
                BotNotificationUtils.create(applicationContext)
            } else BotNotificationUtils.delete(applicationContext)
        }

        sw_bot_power_collapsing.setOnCheckedChangeListener { _, isChecked ->
            DataUtils.saveData(applicationContext, "BotPower", isChecked.toString())
            sw_bot_power_toolbar.isChecked = isChecked
            if (isChecked) {
                BotNotificationUtils.create(applicationContext)
            } else BotNotificationUtils.delete(applicationContext)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.instance())
                .commitNow()
        }

        fab_add.setOnClickListener {
            if (!isOpened) {
                fab_add_bot.show(true, 250)
                fab_setting.show(true, 250)
                fab_add.startAnimation(rotateAnimation(-45f, 0f))
                fab_add.rotation = 45f
            } else {
                fab_add_bot.hide(true, false, 250)
                fab_setting.hide(true, false, 250)
                fab_add.startAnimation(rotateAnimation(45f, 0f))
                fab_add.rotation = 0f
            }
            isOpened = !isOpened
        }

        fab_add_bot.setOnClickListener {
            /*val layout = LayoutInflater
                .from(this)
                .inflate(R.layout.dialog_bot_add, null)

            val etName = layout.findViewById<TextInputEditText>(R.id.et_name)
            val btnJs = layout.findViewById<TextView>(R.id.tv_js)
            val btnSimple = layout.findViewById<TextView>(R.id.tv_simple)

            val alert = AlertDialog.Builder(this)
                .setTitle(getString(R.string.add_new_bot))
                .setView(layout)
                .create()

            btnJs.setOnClickListener {
                alert.cancel()
                val name = etName.text.toString()
                startActivity<ScriptEditActivity>("name" to if (name.isBlank()) null else name)
            }

            alert.show()*/
        }

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

    private fun rotateAnimation(from: Float, to: Float) = RotateAnimation(
        from,
        to,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    ).apply {
        duration = 250
        interpolator = LinearInterpolator()
    }

}