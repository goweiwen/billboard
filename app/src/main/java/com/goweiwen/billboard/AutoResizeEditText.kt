package com.goweiwen.billboard

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * Created by weiwen on 1/31/17.
 */

class AutoResizeEditText : EditText {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        println(text)
        if (text != null && text.isNotEmpty())
            resizeText(text)
    }

    private fun resizeText(text: CharSequence) {
        val maxWidth = (measuredWidth - compoundPaddingLeft - compoundPaddingRight) / context.resources.displayMetrics.density

        paint.textSize = 10f

        val string = text.toString()

        // Search for upper and lower bounds
        var diff = paint.measureText(string) - maxWidth
        var step = 1f

        while (diff < 0f) {
            step *= 2f
            paint.textSize += step
            diff = paint.measureText(string) - maxWidth
        }
        paint.textSize -= step

        // Then search for the perfect size
        while (diff > -1f) {
            step /= 2f
            paint.textSize += step
            diff = paint.measureText(string) - maxWidth
            if (diff > 0f) {
                paint.textSize -= step
                diff = paint.measureText(string) - maxWidth
            }
        }

        textSize = paint.textSize
    }

}