package com.example.geoip.ui.layout.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.widget.FrameLayout

class CustomFrame(context: Context) : FrameLayout(context) {

    companion object {
        private const val STROKE_WIDTH = 8f
    }

    init {
        setWillNotDraw(false)
    }

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = STROKE_WIDTH
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            STROKE_WIDTH,
            STROKE_WIDTH,
            width - STROKE_WIDTH,
            height - STROKE_WIDTH,
            paint
        )
    }
}