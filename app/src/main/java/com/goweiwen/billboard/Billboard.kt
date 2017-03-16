package com.goweiwen.billboard

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_billboard.*

class Billboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        supportActionBar?.hide()
        setContentView(R.layout.activity_billboard)

    }

    override fun onBackPressed() {
        if (!view.isFocused)
            view.requestFocus()
        else
            super.onBackPressed()
    }

}
