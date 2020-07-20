package com.sungbin.chatbot.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.chatbot.model.BotModel
import com.sungbin.chatbot.utils.BotPowerUtils
import com.sungbin.kakaobot.R
import kotlin.random.Random


/**
 * Created by SungBin on 2020-07-20.
 */

class BotListAdapter constructor
    (
    val items: ArrayList<BotModel>,
    val activity: Activity
) :
    RecyclerView.Adapter<BotListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)!!
        val tvRunDay = view.findViewById<TextView>(R.id.tv_bot_run_day)!!
        val swPower = view.findViewById<Switch>(R.id.sw_power)!!
        val tvName = view.findViewById<TextView>(R.id.tv_name)!!
        val tvLastRunTime = view.findViewById<TextView>(R.id.tv_last_run_time)!!
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(viewGroup.context)
                .inflate(
                    R.layout.layout_bot,
                    viewGroup,
                    false
                )
        )

    override fun onBindViewHolder(@NonNull viewholder: ViewHolder, position: Int) {
        var (name, fixed, power, lastRunTime) = items[position]

        name = name.replace(".txt", "")

        viewholder.ivIcon.setImageDrawable(
            when (Random.nextInt(1, 100)) {
                in 1 .. 24 -> ContextCompat.getDrawable(
                    activity,
                    R.drawable.lg_baseline_kakaotalk
                )
                in 25 .. 49 -> ContextCompat.getDrawable(
                    activity,
                    R.drawable.lg_baseline_facebook_messenger
                )
                in 50 .. 75 -> ContextCompat.getDrawable(
                    activity,
                    R.drawable.lg_baseline_telegram
                )
                else -> ContextCompat.getDrawable(activity, R.drawable.lg_baseline_line)
            }
        )

        viewholder.tvName.text = name
        viewholder.swPower.isChecked = power
        viewholder.tvLastRunTime.text = lastRunTime

        viewholder.swPower.setOnCheckedChangeListener { _, checked ->
            BotPowerUtils.setPower(activity, name, checked)
        }
    }

    override fun getItemCount() = items.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
    fun getItem(position: Int) = items[position]
}