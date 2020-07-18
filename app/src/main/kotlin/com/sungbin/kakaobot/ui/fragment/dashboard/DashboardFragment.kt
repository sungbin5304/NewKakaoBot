package com.sungbin.kakaobot.ui.fragment.dashboard


import android.app.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.core.content.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import cn.pedant.SweetAlert.*
import com.github.zawadz88.materialpopupmenu.*
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.listener.*
import com.sungbin.kakaobot.model.*
import com.sungbin.kakaobot.ui.activity.*
import com.sungbin.kakaobot.ui.fragment.*
import com.sungbin.kakaobot.utils.bot.*
import com.sungbin.recyclerviewadaptermaker.library.*
import com.sungbin.recyclerviewadaptermaker.library.options.*
import com.sungbin.sungbintool.*
import com.sungbin.sungbintool.ui.*
import dagger.hilt.android.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.support.v4.*


@AndroidEntryPoint
@WithFragmentBindings
class DashboardFragment : BaseFragment() {

    companion object {
        fun instance() = DashboardFragment()
    }

    interface OnScriptRemovedListener {
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
    }

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = false

        val act = requireContext()

        if (viewModel.jsBot.isEmpty() && viewModel.simpleBot.isEmpty()) {
            rv_bot.hide(false, true)
            cl_bot_none.show(false)
        }

        if (viewModel.jsBot.isNotEmpty()) {
            AdapterHelper
                .with(rv_bot)
                .bindLayout(R.layout.layout_bot)
                .addViewBindListener { item, view, position ->
                    var (name, pinned, power, lastRunTime) = item[position] as BotModel

                    name = name.replace(".js", "")

                    val ivMenu = view.findViewById<ImageView>(R.id.iv_menu)
                    val tvName = view.findViewById<TextView>(R.id.tv_name)
                    val swPower = view.findViewById<Switch>(R.id.sw_power)
                    val ivType = view.findViewById<TagableRoundImageView>(R.id.iv_type)
                    val vReloadStatus = view.findViewById<View>(R.id.view_reload_status)
                    val tvLastRunTime = view.findViewById<TextView>(R.id.tv_last_run_time)

                    tvName.text = name
                    swPower.isChecked = power
                    tvLastRunTime.text = lastRunTime

                    swPower.setOnCheckedChangeListener { _, checked ->
                        BotPowerUtils.setPower(act, name, checked)
                    }

                    ivMenu.setOnClickListener {
                        popupMenu {
                            section {
                                title = act.getString(R.string.edit)
                                item {
                                    labelRes = R.string.bot_name
                                    icon = R.drawable.ic_baseline_title_24
                                    callback = {
                                        ToastUtils.show(
                                            act, "눌림",
                                            ToastUtils.SHORT, ToastUtils.INFO
                                        )
                                    }
                                }
                                item {
                                    labelRes = R.string.description
                                    icon = R.drawable.ic_baseline_description_24
                                    callback = {
                                        ToastUtils.show(
                                            act, "눌림",
                                            ToastUtils.SHORT, ToastUtils.INFO
                                        )
                                    }
                                }
                                item {
                                    labelRes = R.string.source
                                    icon = R.drawable.ic_baseline_code_24
                                    callback = {
                                        startActivity<ScriptEditActivity>(
                                            "name" to name, "script" to StorageUtils.read(
                                                "${BotPathManager.JS}/$name.js",
                                                "const response = (room, msg, sender, isGroupChat, replier, ImageDB, packageName) => {\n}"
                                            )
                                        )
                                    }
                                }
                            }
                            section {
                                title = act.getString(R.string.other)
                                item {
                                    labelRes = R.string.reload
                                    icon = R.drawable.ic_baseline_autorenew_24
                                    callback = {
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val ms1 = System.currentTimeMillis()
                                            val pDialog = SweetAlertDialog(
                                                act,
                                                SweetAlertDialog.PROGRESS_TYPE
                                            )
                                            pDialog.progressHelper.barColor =
                                                ContextCompat.getColor(
                                                    act,
                                                    R.color.colorPrimary
                                                )
                                            pDialog.titleText =
                                                act.getString(R.string.reloading)
                                            pDialog.setCancelable(false)
                                            pDialog.show()

                                            val reloading = async {
                                                KakaoTalkListener.initializeJavaScript(name)
                                            }
                                            val status = reloading.await()

                                            val ms2 = System.currentTimeMillis()
                                            val reloadTime = (ms2 - ms1).toString()
                                            pDialog.confirmText = "닫기"
                                            if (status != "true") {
                                                pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE)
                                                pDialog.titleText = "리로드 실패"
                                                pDialog.contentText = "리로드중 오류가 발생했습니다.<br>" +
                                                        "<font color=#EF5350>$status</font>"

                                                vReloadStatus.backgroundTintList =
                                                    ContextCompat.getColorStateList(
                                                        act,
                                                        R.color.colorPink
                                                    )

                                                if (!DataUtils.readData(act, "KeepScope", "false")
                                                        .toBoolean()
                                                ) {
                                                    swPower.isChecked = false
                                                    BotPowerUtils.setPower(act, name, false)
                                                }
                                            } else {
                                                pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                                                pDialog.titleText = "리로드 성공"
                                                pDialog.contentText = "리로드가 완료되었습니다.<br>" +
                                                        "<font color=#4CAF50>리로드 시간 : $reloadTime ms</font>"

                                                vReloadStatus.backgroundTintList =
                                                    ContextCompat.getColorStateList(
                                                        act,
                                                        R.color.colorGreen
                                                    )
                                            }
                                        }
                                    }
                                }
                                item {
                                    labelRes = R.string.share
                                    icon = R.drawable.ic_baseline_share_24
                                    callback = {
                                        ToastUtils.show(
                                            act, "눌림",
                                            ToastUtils.SHORT, ToastUtils.INFO
                                        )
                                    }
                                }
                            }
                            section {
                                title = act.getString(R.string.danger)
                                item {
                                    labelRes = R.string.delete
                                    icon = R.drawable.ic_baseline_delete_24
                                    callback = {
                                        StorageUtils.delete("${BotPathManager.JS}/$name.js")
                                        ToastUtils.show(act, getString(R.string.deleted), ToastUtils.SHORT, ToastUtils.SUCCESS)
                                    }
                                    if (listener != null) listener!!.onRemoved()
                                }
                            }
                        }.show(act, it)
                    }
                }
                .addOption(Option(null, Padding(0, 0, 16, 0)))
                .create(viewModel.jsBot)

            rv_bot.layoutManager =
                LinearLayoutManager(activity as Activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}