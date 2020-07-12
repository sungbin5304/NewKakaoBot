package com.sungbin.kakaobot.ui.activity

import android.annotation.*
import android.app.*
import android.os.*
import android.text.*
import android.view.*
import android.view.inputmethod.*
import android.widget.*
import androidx.annotation.*
import androidx.appcompat.app.*
import androidx.core.content.res.*
import androidx.core.view.*
import androidx.core.widget.*
import androidx.recyclerview.widget.*
import com.balsikandar.crashreporter.*
import com.github.zawadz88.materialpopupmenu.*
import com.google.android.material.textfield.*
import com.sungbin.kakaobot.R
import com.sungbin.kakaobot.adapter.*
import com.sungbin.kakaobot.model.*
import com.sungbin.kakaobot.utils.bot.*
import com.sungbin.sungbintool.*
import kotlinx.android.synthetic.main.activity_js_edit.*
import kotlinx.android.synthetic.main.content_js_edit.*
import org.mozilla.javascript.*
import org.mozilla.javascript.ast.*
import java.util.*
import kotlin.collections.ArrayList


class ScriptEditActivity : AppCompatActivity() {

    private val classNameList = ArrayList<String>()
    private val timer = Timer()

    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_js_edit)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        DialogUtils.showOnce(
            this,
            getString(R.string.experimental_function),
            getString(R.string.editor_experimental_function_description),
            "experimental_editor2",
            null, false
        )

        val editor = sce_editor.editor
        editor.setText(
            intent.getStringExtra("script")
                ?: "const response = (room, msg, sender, isGroupChat, replier, ImageDB, packageName) => {\n\n}"
        )
        val scriptName = intent.getStringExtra("name") ?: "New Script"
        val textSize = DataUtils.readData(applicationContext, "TextSize", "17").toInt()
        val autoSave = DataUtils.readData(applicationContext, "AutoSave", "true").toBoolean()
        val notHighting = DataUtils.readData(applicationContext, "NotHighting", "false").toBoolean()
        val notErrorHighting =
            DataUtils.readData(applicationContext, "NotErrorHighting", "false").toBoolean()

        val headerView = LayoutInflater
            .from(applicationContext)
            .inflate(R.layout.header_layout_editor_find, null, false)

        val etFind = headerView.findViewById<TextInputEditText>(R.id.et_find)
        val swIgnoreUpper = headerView.findViewById<Switch>(R.id.sw_ignore)
        val rvList = headerView.findViewById<RecyclerView>(R.id.rv_list)

        rvList.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        etFind.imeOptions = EditorInfo.IME_ACTION_SEARCH
        etFind.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                val items = ArrayList<EditorFindItem>()
                val result = sce_editor.findText(
                    etFind.text.toString(),
                    swIgnoreUpper.isChecked
                )
                if (result.isNotEmpty()) {
                    for (i in result.indices) {
                        val array = result[i]
                        val item = EditorFindItem(
                            editor.text.split("\n")[array[0]],
                            etFind.text.toString(),
                            array[0],
                            array[1]
                        )
                        items.add(item)
                    }
                } else {
                    items.add(
                        EditorFindItem(
                            getString(R.string.find_none),
                            "null",
                            -1,
                            -1
                        )
                    )
                }
                val adapter = EditorFindAdapter(items)
                adapter.setOnItemClickListener { findText, _, line, index ->
                    if (index > 0) {
                        val i = getStartIndex(editor, line, index)
                        editor.setSelection(i, i + findText.length)
                    }
                    dl_drawer.closeDrawer(GravityCompat.START)
                }
                rvList.adapter = adapter
                adapter.notifyDataSetChanged()
            } else return@setOnEditorActionListener false
            return@setOnEditorActionListener true
        }

        nv_navigation.addHeaderView(headerView)

        val typeface = ResourcesCompat.getFont(applicationContext, R.font.d2coding)
        editor.typeface = typeface

        editor.textSize = textSize.toFloat()
        if (autoSave) {
            timer.schedule(
                AutoSaveTimer(
                    this,
                    editor,
                    scriptName
                ),
                300000,
                300000
            )
        }

        toolbar_title.text = scriptName
        if (notHighting) sce_editor.applyHighlight = false

        val suggestList: ArrayList<String> = ArrayList()
        val list = ArrayList<String>()
        val items = arrayOf(
            "String",
            "File",
            "java",
            "io",
            "Array",
            "int",
            "function",
            "return",
            "var",
            "let",
            "const",
            "if",
            "else",
            "switch",
            "for",
            "while",
            "do",
            "break",
            "continue",
            "case",
            "in",
            "with",
            "true",
            "false",
            "new",
            "null",
            "undefined",
            "typeof",
            "delete",
            "try",
            "catch",
            "finally",
            "prototype",
            "this",
            "super",
            "default",
            "prototype"
        )
        for (element in items) list.add(element)

        ib_save.setOnClickListener {
            StorageUtils.save(
                "${BotPathManager.JS}/$scriptName.js",
                editor.text.toString()
            )
            ToastUtils.show(
                applicationContext,
                getString(R.string.saved),
                ToastUtils.SHORT,
                ToastUtils.SUCCESS
            )
            Utils.copy(applicationContext, editor.text.toString())
        }

        ib_menu.setOnClickListener {
            popupMenu {
                section {
                    title = getString(R.string.editor)
                    item {
                        labelRes = R.string.undo
                        icon = R.drawable.ic_baseline_undo_24
                        callback = {
                            sce_editor.undo()
                        }
                    }
                    item {
                        labelRes = R.string.redo
                        icon = R.drawable.ic_baseline_redo_24
                        callback = {
                            sce_editor.redo()
                        }
                    }
                    item {
                        labelRes = R.string.search
                        icon = R.drawable.ic_baseline_search_24
                        callback = {
                            dl_drawer.openDrawer(GravityCompat.START)
                        }
                    }
                    item {
                        labelRes = R.string.replace
                        icon = R.drawable.ic_baseline_find_replace_24
                        callback = {
                            //바꾸기
                        }
                    }
                }
                section {
                    title = getString(R.string.setting)
                    item {
                        labelRes = R.string.highlighting
                        icon = R.drawable.ic_baseline_highlight_24
                        callback = {
                            //하이라이트 설정
                        }
                    }
                    item {
                        labelRes = R.string.auto_complete
                        icon = R.drawable.ic_baseline_flash_auto_24
                        callback = {
                            //자동완성 설정
                        }
                    }
                }
                section {
                    title = getString(R.string.other)
                    item {
                        labelRes = R.string.save
                        icon = R.drawable.ic_baseline_save_24
                        callback = {
                            StorageUtils.save(
                                "${BotPathManager.JS}/$scriptName.js",
                                editor.text.toString()
                            )
                            ToastUtils.show(
                                applicationContext,
                                getString(R.string.saved),
                                ToastUtils.SHORT,
                                ToastUtils.SUCCESS
                            )
                            Utils.copy(applicationContext, editor.text.toString())
                        }
                    }
                }
            }.show(applicationContext, it)
        }

        action_indent.setOnClickListener { editor.insert("\t\t\t\t") }
        action_undo.setOnClickListener { sce_editor.undo() }
        action_redo.setOnClickListener { sce_editor.redo() }
        action_right_big.setOnClickListener { editor.insert("{") }
        action_left_big.setOnClickListener { editor.insert("}") }
        action_right_small.setOnClickListener { editor.insert("(") }
        action_left_small.setOnClickListener { editor.insert(")") }
        action_big_quote.setOnClickListener { editor.insert("\"") }
        action_small_quote.setOnClickListener { editor.insert("'") }
        action_dot.setOnClickListener { editor.insert(".") }
        action_end.setOnClickListener { editor.insert(";") }
        action_plus.setOnClickListener { editor.insert("+") }
        action_minus.setOnClickListener { editor.insert("-") }
        action_underbar.setOnClickListener { editor.insert("_") }

        editor.setPadding(16, 16, 16, 16)
        editor.doAfterTextChanged {
            try {
                suggestList.clear()
                loadClassName(it.toString())
                val layout: Layout = editor.layout
                val selectionStart = Selection.getSelectionStart(editor.text)
                val now = it.toString().split("\n")[layout.getLineForOffset(selectionStart)]
                    .trim() //지금 쓰고있는 단어 가져오기
                    .split(" ")[it.toString().split("\n")
                        [layout.getLineForOffset(selectionStart)]
                    .trim().split(" ").size - 1]
                val all = it.toString()
                for (element in classNameList) {
                    if (!list.contains(element))
                        list.add(element)
                }
                for (i in list.indices) {
                    if (list[i].startsWith(now) && list[i] != now
                        && now.isNotBlank() && now.replace(" ", "").length > 1
                    ) {
                        suggestList.add(list[i])
                    }
                }
                if (suggestList.size == 0) {
                    append_auto.visibility = View.GONE
                } else {
                    if (suggestList.isNotEmpty()) {
                        if (suggestList.size == 1) {
                            append_auto.visibility = View.VISIBLE
                            append_auto.text = suggestList[0]
                            append_auto.setOnClickListener {
                                append_auto.visibility = View.GONE
                                try {
                                    editor.insert(suggestList[0].replace(now, ""))
                                } catch (ignored: Exception) {
                                }
                            }
                        }
                    } else if (suggestList.size != list.size &&
                        !all.split("\n")[layout.getLineForOffset(selectionStart)].isBlank()
                    ) {
                        append_auto.visibility = View.VISIBLE
                        append_auto.text = getString(R.string.auto_complete_with_enter)
                        append_auto.setOnClickListener { view ->
                            val p = PopupMenu(applicationContext, view)
                            for (i in 0 until suggestList.size) {
                                p.menu.add(0, i, 0, suggestList[i])
                            }
                            p.setOnMenuItemClickListener { menuItem ->
                                append_auto.visibility = View.GONE
                                editor.insert(
                                    suggestList[menuItem.itemId].replace(now, "")
                                )
                                return@setOnMenuItemClickListener false
                            }
                            p.show()
                        }
                    }
                }
            } catch (e: Exception) {
                CrashReporter.logException(e)
            }
        }
    }

    private fun getStartIndex(editText: EditText, line: Int, index: Int): Int {
        var i = 0
        val texts = editText.text.split("\n")
        for (n in 0 .. line) i += texts[n].length
        return i + index
    }

    override fun onBackPressed() {
        super.onBackPressed()
        timer.cancel()
    }

    private fun loadClassName(source: String) {
        try {
            classNameList.clear()
            val env = CompilerEnvirons()
            env.languageVersion = Context.VERSION_ES6
            env.optimizationLevel = -1
            val parser = Parser(env)
            val nv = NodeVisitor { node ->
                val type = node.javaClass.name
                if (type == "org.mozilla.javascript.ast.Name") {
                    val name = node.toSource()
                    classNameList.add(name)
                }
                true
            }
            parser.parse(source, null, 1).visitAll(nv)
        } catch (e: Exception) {
        }
    }

    private fun EditText.insert(tag: String) {
        this.text.insert(this.selectionStart, tag)
    }

    private class AutoSaveTimer
    constructor(
        private val activity: Activity,
        private val editText: EditText,
        private val scriptName: String
    ) : TimerTask() {

        override fun run() {
            StorageUtils.save(
                "${BotPathManager.JS}/$scriptName.js",
                editText.text.toString()
            )

            activity.runOnUiThread {
                ToastUtils.show(
                    activity,
                    activity.getString(R.string.auto_saved),
                    ToastUtils.SHORT,
                    ToastUtils.SUCCESS
                )
                Utils.copy(activity, editText.text.toString())
            }
        }
    }

}
