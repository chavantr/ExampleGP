package com.mywings.photographpassword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.mywings.photographpassword.database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_verification.*


class VerificationActivity : AppCompatActivity() {


    val clusterX = arrayOf(25, 55, 80, 105, 75, 35, 15, 10)
    val clusterXE = arrayOf(40, 115, 175, 200, 150, 70, 10, 5)
    val clusterY = arrayOf(5, 5, 10, 50, 85, 90, 65, 30)
    val clusterYE = arrayOf(10, 0, 20, 120, 185, 190, 150, 70)
    val inner = arrayOf('3', '5', '7', '1', 'b', 'b', 'f', 'h')
    val outer = arrayOf('a', 'd', 'e', 'g', '2', '4', '6', '8')


    private val images = arrayOf(R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i8)
    private var i: Int = 0

    private lateinit var databaseHelper: DatabaseHelper
    private var user: User? = null
    private var email: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        databaseHelper = DatabaseHelper(this, "graphicpass", null, 1)
        if (intent.hasExtra("email")) {
            lblEmail.text = intent.getStringExtra("email")
            email = intent.getStringExtra("email")
            user = databaseHelper.getUsers(intent.getStringExtra("email"))
        }


        for ((i, v) in clusterX.withIndex()) {

            val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val paramsEx = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)


            val view = TextView(applicationContext)
            val viewEx = TextView(applicationContext)
            view.text = inner[i].toString()
            viewEx.text = outer[i].toString()
            view.setTextColor(Color.BLACK)
            viewEx.setTextColor(Color.BLACK)
            params.leftMargin = pxToDp((flInner.left + v))
            params.topMargin = pxToDp((flInner.top + clusterY[i]))
            paramsEx.leftMargin = pxToDp((flOuter.left + clusterXE[i]))
            paramsEx.topMargin = pxToDp((flOuter.top + clusterYE[i]))
            flOuter.addView(viewEx, paramsEx)
            flInner.addView(view, params)
        }

        btnClockWise.setOnClickListener {

            if (i == 8) i = 0

            rlFlow.setBackgroundResource(images[i])
            i++
        }

        btnAntiClockWise.setOnClickListener {

            i--
            if (i == -1) i = 7

            rlFlow.setBackgroundResource(images[i])


        }

        btnVerify.setOnClickListener {

            if (user!!.password.contentEquals(txtPassword.text.toString())) {
                val intent = Intent(this@VerificationActivity, DashboardActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            } else {
                show("Enter correct password")
            }
        }


    }

    private fun show(message: String) {
        Snackbar.make(btnVerify, message, Snackbar.LENGTH_LONG).setAction("OK", {}).show()
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

            DisplayMetrics.DENSITY_XXXHIGH ->
                dp = Math.round(px * 4.0).toInt()

            else ->
                dp = Math.round(px * 1.0).toInt()
        }

        return dp
    }
}
