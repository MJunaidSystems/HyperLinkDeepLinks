package com.example.hyperlinkdeeplinkapp

import android.content.Context
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomClickableTextView(context: Context, attrs: AttributeSet) :
    AppCompatTextView(context, attrs) {

    private var mOnItemClickListener: UrlClickListener? = null

    init {
        movementMethod = LinkMovementMethod.getInstance()

        setOnTouchListener { view, event ->
            val textView = view as AppCompatTextView

            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    val touchX = event.x.toInt()
                    val touchY = event.y.toInt()

                    val x = touchX.toFloat() + textView.scrollX
                    val y = touchY.toFloat() + textView.scrollY

                    val layout = textView.layout
                    val line = layout.getLineForVertical(y.toInt())
                    val off = layout.getOffsetForHorizontal(line, x)

                    val spannable = textView.text as Spannable
                    val linkSpans =
                        spannable.getSpans(off, off, android.text.style.URLSpan::class.java)

                    if (linkSpans.isNotEmpty()) {
                        val url = (linkSpans[0] as android.text.style.URLSpan).url
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
                        mOnItemClickListener?.onSpannableStringTapped(url)
                        performClick() // Perform click action
                    }
                }
            }

            true
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    fun setOnItemClickListener(listener: UrlClickListener) {
        mOnItemClickListener = listener
    }
}