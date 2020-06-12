package com.sungbin.kakaobot.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.sungbin.sungbintool.LogUtils
import com.sungbin.sungbintool.ToastUtils


/**
 * Created by SungBin on 2020-06-12.
 */

open class BaseFragment : Fragment(){
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
}