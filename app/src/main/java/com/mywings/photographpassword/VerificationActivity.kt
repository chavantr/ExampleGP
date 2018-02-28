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


    val clusterX = arrayOf(25, 55, 80, 95, 75, 35, 15, 10)
    val clusterXE = arrayOf(40, 115, 175, 190, 150, 70, 10, 5)
    val clusterY = arrayOf(5, 5, 10, 50, 85, 90, 65, 30)
    val clusterYE = arrayOf(10, 0, 20, 120, 185, 190, 150, 70)
    val clusterLine = arrayOf(0, 0, 20, 120, 185, 190, 150, 70)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        if (intent.hasExtra("email")) {
            lblEmail.text = intent.getStringExtra("email")
        }


        for ((i, v) in clusterX.withIndex()) {

            val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val paramsEx = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val paramsLine = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)

            val view = TextView(applicationContext)
            val viewEx = TextView(applicationContext)
            val viewLine = TextView(applicationContext)



            view.text = "5"

            viewEx.text = "7"

            viewLine.text ="-----------------------------------------------"

            view.setTextColor(Color.BLACK)
            viewEx.setTextColor(Color.BLACK)
            viewLine.setTextColor(Color.BLACK)
            //viewLine.setBackgroundColor(Color.BLACK)

            params.leftMargin = pxToDp((flInner.left + v))

            params.topMargin = pxToDp((flInner.top + clusterY[i]))


            paramsEx.leftMargin = pxToDp((flOuter.left + clusterXE[i]))
            paramsEx.topMargin = pxToDp((flOuter.top + clusterYE[i]))


            paramsLine.leftMargin = pxToDp((flFormat.left + 0))
            paramsLine.topMargin = pxToDp((flFormat.top + 0))




            flOuter.addView(viewEx,paramsEx)

            flFormat.addView(viewLine,paramsLine)

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
