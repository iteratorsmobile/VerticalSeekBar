package com.lukelorusso.verticalseekbarsample

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.lukelorusso.verticalseekbarsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var thumbProgress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.zoomSeekBar.apply {
            progress = 75

            setOnProgressChangeListener { progressValue ->
                Log.d("VerticalSeekBar", "PROGRESS CHANGED at value: $progressValue")
                thumbProgress.text = progressValue.toString()
            }

            setOnPressListener { progressValue ->
                Log.d("VerticalSeekBar", "PRESSED at value: $progressValue")
            }

            setOnReleaseListener { progressValue ->
                Log.d("VerticalSeekBar", "RELEASED at value: $progressValue")
            }

        }
        initCustomThumb()
    }

    private fun initCustomThumb() {
        val resources = this.resources
        binding.zoomSeekBar.setBarMargin = false
        binding.zoomSeekBar.thumbCustomView = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.zoom_thumb_width),
                resources.getDimensionPixelSize(R.dimen.zoom_thumb_height)
            )
            gravity = Gravity.CENTER

            thumbProgress = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setTextColor(ContextCompat.getColor(context, android.R.color.white))
                isAllCaps = true
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                gravity = Gravity.CENTER
            }

            val zoomTextView = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setTextColor(ContextCompat.getColor(context, android.R.color.white))
                isAllCaps = true
                text = context.getString(R.string.zoom)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                gravity = Gravity.CENTER
            }

            this.addView(zoomTextView)
            this.addView(thumbProgress)
        }
    }
}
