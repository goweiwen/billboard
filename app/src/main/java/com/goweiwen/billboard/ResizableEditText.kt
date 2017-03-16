package com.goweiwen.billboard

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by weiwen on 3/16/17.
 */

class ResizableEditText : EditText {
    val _textSize = textSize
    var scaleFactor = 1f

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val scaleGestureDetector = ScaleGestureDetector(context,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 3.0f))
                    textSize = _textSize * scaleFactor
                    return true
                }
            }
    )

    private val gestureDetector = GestureDetector(context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    this@ResizableEditText.requestFocus()
                    this@ResizableEditText.postDelayed({
                        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(this@ResizableEditText, InputMethodManager.SHOW_FORCED)
                    }, 200)
                    return false
                }

//                override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//                    this@ResizableEditText.translationX -= distanceX
//                    this@ResizableEditText.translationY -= distanceY
//                    return true
//                }

            }
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress)
            gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onEditorAction(actionCode: Int) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            clearFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        } else
            super.onEditorAction(actionCode)
    }

}