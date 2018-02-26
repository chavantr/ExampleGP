package com.mywings.photographpassword

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_verification.*
import android.util.DisplayMetrics


class VerificationActivity : AppCompatActivity() {


    val clusterX = arrayOf(5, 30, 60, 80, 95, 120, 150, 180)
    val clusterY = arrayOf(5, 45, 70, 95, 110, 89, 130, 180)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        if (intent.hasExtra("email")) {
            lblEmail.text = intent.getStringExtra("email")
        }


        for ((i, v) in clusterX.withIndex()) {

            val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val view = TextView(applicationContext)

            view.text = "50"

            view.setTextColor(Color.BLACK)

            params.leftMargin = pxToDp((flInner.left + v))

            params.topMargin = pxToDp((flInner.top + clusterY[i]))

            flInner.addView(view, params)


        }

    }

    private fun show(message: String) {
        Snackbar.make(btnLogin, message, Snackbar.LENGTH_LONG).setAction("OK", {}).show()
    }

    private fun pxToDp(px: Int): Int {

        var dp: Int

        when (resources.displayMetrics.densityDpi) {

            DisplayMetrics.DENSITY_LOW ->
                dp = Math.round(px * 0.75).toInt()

            DisplayMetrics.DENSITY_MEDIUM ->
                dp = Math.round(px * 1.0).toInt()

            DisplayMetrics.DENSITY_HIGH ->
                dp = Math.round(px * 1.5).toInt()

            DisplayMetrics.DENSITY_XHIGH ->
                dp = Math.round(px * 2.0).toInt()

            DisplayMetrics.DENSITY_XXHIGH ->
                dp = Math.round(px * 3.0).toInt()

            else ->
                dp = Math.round(px * 1.0).toInt()
        }

        return dp
    }
}
