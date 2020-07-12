package com.sungbin.kakaobot.ui.activity

import android.animation.*
import android.content.*
import android.view.*
import androidx.appcompat.app.*
import com.daimajia.androidanimations.library.*
import com.sungbin.sungbintool.*


/**
 * Created by SungBin on 2020-06-13.
 */

open class BaseActivity : AppCompatActivity() {
    fun String.toastS(context: Context?, isLong: Boolean = false) {
        context?.run {
            ToastUtils.show(
                context,
                this@toastS,
                if (isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.SUCCESS
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun String.toastW(context: Context?, isLong: Boolean = false) {
        context?.run {
            ToastUtils.show(
                context,
                this@toastW,
                if (isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.WARNING
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun String.toastE(context: Context?, isLong: Boolean = false) {
        context?.run {
            ToastUtils.show(
                context,
                this@toastE,
                if (isLong) ToastUtils.LONG else ToastUtils.SHORT,
                ToastUtils.ERROR
            )
        } ?: LogUtils.e("Toast Context is null.")
    }

    fun View.show(showAnimation: Boolean = true, duration: Long = 500) {
        if (showAnimation) {
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
                .duration(duration)
                .playOn(this)
        } else {
            this.visibility = View.VISIBLE
        }
    }

    fun View.hide(
        showAnimation: Boolean = true,
        isInvisible: Boolean = false,
        duration: Long = 500
    ) {
        if (showAnimation) {
            YoYo
                .with(Techniques.FadeOut)
                .withListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        if (isInvisible) {
                            this@hide.visibility = View.INVISIBLE
                        } else {
                            this@hide.visibility = View.GONE
                        }
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationStart(p0: Animator?) {
                    }

                })
                .duration(duration)
                .playOn(this)
        } else {
            if (isInvisible) {
                this@hide.visibility = View.INVISIBLE
            } else {
                this@hide.visibility = View.GONE
            }
        }
    }
}