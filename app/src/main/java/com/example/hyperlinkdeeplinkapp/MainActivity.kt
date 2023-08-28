package com.example.hyperlinkdeeplinkapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import java.util.Objects

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val htmlTextView: TextView = findViewById(R.id.htmlTextView)

        val htmlContent =
            "This is <a href=\"https://www.google.com\">Google</a> and this is <a href=\"https://www.chat.openai.com\">ChatGPT</a>"
        */
/* val formattedHtml = HtmlCompat.fromHtml(htmlContent, HtmlCompat.FROM_HTML_MODE_LEGACY, null, MyTagHandler())

         htmlTextView.text = formattedHtml
         htmlTextView.movementMethod = LinkMovementMethod.getInstance()*//*

        val formattedHtml =
            HtmlCompat.fromHtml(htmlContent, HtmlCompat.FROM_HTML_MODE_LEGACY, null, null)

        htmlTextView.text = formattedHtml
        htmlTextView.movementMethod = LinkMovementMethod.getInstance()

        htmlTextView.setOnTouchListener { view, event ->
            val textView = view as TextView
            val action = event.action

            if (action == MotionEvent.ACTION_UP) {
                val touchX = event.x.toInt()
                val touchY = event.y.toInt()

                val x = touchX.toFloat() + textView.scrollX
                val y = touchY.toFloat() + textView.scrollY

                val layout = textView.layout
                val line = layout.getLineForVertical(y.toInt())
                val off = layout.getOffsetForHorizontal(line, x)

                val spannable = textView.text as Spannable
                val linkSpans = spannable.getSpans(off, off, URLSpan::class.java)

                if (linkSpans.isNotEmpty()) {
                    val url = (linkSpans[0] as URLSpan).url
                    Toast.makeText(this@MainActivity, "URL: $url", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    */
/*  private inner class MyTagHandler : Html.TagHandler {
          private val linkStartIndices = mutableListOf<Int>()
          override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
              if (tag.equals("a", ignoreCase = true)) {
                  if (opening) {
                      linkStartIndices.add(output.length)
                  } else {
                      if (linkStartIndices.isNotEmpty()) {
                          val startIndex = linkStartIndices.removeAt(linkStartIndices.size - 1)
                          val endIndex = output.length
                          val url = output.subSequence(startIndex, endIndex).toString()
                          output.setSpan(MyClickableSpan(url), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                      }
                  }
              }
          }
      }

      private inner class MyClickableSpan(private val url: String) : ClickableSpan() {
          override fun onClick(widget: View) {
              // Handle the click here using the stored URL
              println("Clicked URL: $url")
             // Toast.makeText(this@MainActivity, "Clicked URL: $url", Toast.LENGTH_SHORT).show()
          }
      }*//*

}*/


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customClickableTextView: CustomClickableTextView = findViewById(R.id.htmlTextView)
        customClickableTextView.setOnItemClickListener(onSpanStringClicked)

        val htmlContent =
            "This is <a href=\"https://www.google.com\">Google</a> and this is <a href=\"https://www.chat.openai.com\">ChatGPT</a>"
        val formattedHtml =
            HtmlCompat.fromHtml(htmlContent, HtmlCompat.FROM_HTML_MODE_LEGACY, null, null)

        customClickableTextView.text = formattedHtml
    }

    private val onSpanStringClicked = object : UrlClickListener {
        override fun onSpannableStringTapped(url: String) {
            Toast.makeText(this@MainActivity, url, Toast.LENGTH_SHORT).show()
        }
    }
}


