package com.example.geoip.ui.layout.items

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.geoip.R
import com.example.geoip.ui.layout.custom.CustomFrame

class RecyclerItem(private val context: Context) {

    lateinit var container: LinearLayout
    private lateinit var customFrame1: CustomFrame
    private lateinit var customFrame2: CustomFrame
    lateinit var keyTextView: TextView
    lateinit var valueTextView: TextView

    fun build(): RecyclerItem {
        container = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            orientation = LinearLayout.HORIZONTAL
        }

        customFrame1 = getCustomFrame(1f)
        customFrame2 = getCustomFrame(2f)

        keyTextView = getTextView()
        valueTextView = getTextView()

        container.apply {
            addView(customFrame1.apply {
                addView(keyTextView)
            })
            addView(customFrame2.apply {
                addView(valueTextView)
            })
        }
        return this
    }

    private fun getTextView(): TextView = TextView(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        ).apply {
            setMargins(16, 32, 16, 32)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Headline6)
        } else {
            setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Headline6)
        }
        setTextColor(Color.BLACK)
        gravity = Gravity.CENTER
    }

    private fun getCustomFrame(frameWeight: Float) = CustomFrame(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.MATCH_PARENT,
        ).apply {
            weight = frameWeight
            gravity = Gravity.CENTER
        }
    }
}