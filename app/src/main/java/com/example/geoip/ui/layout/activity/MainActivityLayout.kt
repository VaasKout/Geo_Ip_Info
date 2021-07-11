package com.example.geoip.ui.layout.activity

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geoip.R
import com.example.geoip.data.res.Strings
import com.google.android.material.snackbar.Snackbar

class MainActivityLayout(private val context: Context) {

    lateinit var mainContainer: LinearLayout
    lateinit var progressBarLinearLayout: LinearLayout
    lateinit var stateTextView: TextView
    private lateinit var progressBar: ProgressBar
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView

    fun build(): MainActivityLayout {
        mainContainer = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        progressBarLinearLayout = LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            ).apply {
                setPadding(32, 32, 32, 32)
                gravity = Gravity.END
            }
            orientation = LinearLayout.HORIZONTAL
        }

        stateTextView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                setMargins(8, 0, 8, 0)
            }
            gravity = Gravity.CENTER
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextAppearance(R.style.TextAppearance_MaterialComponents_Body2)
            } else {
                setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Body2)
            }
            text = Strings.loading
            setTextColor(Color.BLACK)
        }

        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleSmall).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
                setMargins(8, 0, 8, 0)
            }
            indeterminateDrawable?.colorFilter =
                PorterDuffColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY)
        }

        toolbar = Toolbar(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            setBackgroundColor(Color.MAGENTA)
            title = Strings.appName
            setTitleTextColor(Color.WHITE)
            menu.apply {
                add(Strings.refresh).setIcon(R.drawable.ic_refresh)
                    .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            }
        }

        recyclerView = RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            ).apply {
                setMargins(8, 8, 8, 8)
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        mainContainer.apply {
            addView(toolbar)
            addView(progressBarLinearLayout.apply {
                addView(stateTextView)
                addView(progressBar)
            })
            addView(recyclerView)
        }
        return this
    }

    fun setLoadingState() {
        progressBarLinearLayout.visibility = View.VISIBLE
    }

    fun setSuccessState() {
        progressBarLinearLayout.visibility = View.GONE
    }

    fun setErrorState(msg: String) {
        progressBarLinearLayout.visibility = View.GONE
        Snackbar.make(
            mainContainer,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }
}