package com.sungbin.kakaobot.ui.activity

import android.animation.Animator
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.sungbin.sungbintool.LogUtils
import com.sungbin.sungbintool.ToastUtils


/**
 * Created by SungBin on 2020-06-13.
 */

open class BaseActivity : AppCompatActivity() {
    fun String.toastS(context: Context?, isLong: Boolean = false){
        context?.run {
            ToastUtils.show(
                context,
                this@toastS,
                if(isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.SUCCESS
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun String.toastW(context: Context?, isLong: Boolean = false){
        context?.run {
            ToastUtils.show(
                context,
                this@toastW,
                if(isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.WARNING
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun String.toastE(context: Context?, isLong: Boolean = false){
        context?.run {
            ToastUtils.show(
                context,
                this@toastE,
                if(isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.ERROR
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun View.show() {
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

    fun View.hide() {
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