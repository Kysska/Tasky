package com.example.tasky.utilites

import android.os.Build
import android.text.Html
import android.text.Spanned

object HTMLManager {

    fun getFromHtml(text: String): Spanned{
        return if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            Html.fromHtml(text)
        } else{
            Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    fun toHtml(text: Spanned): String{
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            Html.toHtml(text)
        } else {
            Html.toHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}