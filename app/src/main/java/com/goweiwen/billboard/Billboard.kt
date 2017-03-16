package com.goweiwen.billboard

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.pawegio.kandroid.textWatcher
import kotlinx.android.synthetic.main.activity_billboard.*

class Billboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        supportActionBar?.hide()
        setContentView(R.layout.activity_billboard)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editText.textWatcher {
            onTextChanged { text, start, before, count ->
                if (text != null && text.isNotEmpty() && start < text.length && start >= 0 &&
                        text.subSequence(start, start + 1).toString().equals("\n", true)) {

                    var s_text = ""
                    if (start > 0) s_text += text.subSequence(0, start)
                    if (start < text.length) s_text += text.subSequence(start + 1, text.length)
                    editText.setText(s_text.trim())

                    val imm = this@Billboard.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(editText.windowToken, 0)
                    editText.clearFocus()
                }
            }
        }
    }

}
