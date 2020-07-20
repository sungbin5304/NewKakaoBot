package com.sungbin.chatbot.ui.fragment.main


import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.chatbot.adapter.BotListAdapter
import com.sungbin.chatbot.adapter.FixedBotListAdapter
import com.sungbin.chatbot.model.BotModel
import com.sungbin.chatbot.ui.fragment.BaseFragment
import com.sungbin.kakaobot.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
@WithFragmentBindings
class MainFragment : BaseFragment() {

    companion object {
        fun instance() = MainFragment()
    }

    /*interface OnScriptRemovedListener {
        fun onRemoved()
    }

    private var listener: OnScriptRemovedListener? = null
    fun setOnScriptRemovedListener(listener: OnScriptRemovedListener?) {
        this.listener = listener
    }

    fun setOnScriptRemovedListener(listener: () -> Unit) {
        this.listener = object : OnScriptRemovedListener {
            override fun onRemoved() {
                listener()
            }
        }
    }*/

    @Inject
    @Named("TEST")
    lateinit var testBot: ArrayList<BotModel>

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = false

        val activity = requireActivity()

        val adapter1 = BotListAdapter(testBot, activity)
        val adapter2 = FixedBotListAdapter(testBot, activity)

        rv_bot.adapter = adapter1
        rv_fixed_bot.adapter = adapter2

        rv_bot.addItemDecoration(recyclerViewPadding(bottom = 30))
        rv_fixed_bot.addItemDecoration(recyclerViewPadding(left = 30))

        tv_fixed.visibility = View.VISIBLE
        rv_fixed_bot.visibility = View.VISIBLE
    }

    private fun recyclerViewPadding(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
    ) = object : RecyclerView.ItemDecoration() { //아이템 간격
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount) {
                outRect.set(left, top, right, bottom)
            }
        }
    }
}