package com.mywings.photographpassword

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_otpverification.*


class OTPVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)


        btnVerify.setOnClickListener {

            if (txtOtp.text.isNotEmpty() && txtOtp.text.toString().contentEquals("000000")) {
                val intent = Intent(this@OTPVerificationActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                show("Enter valid otp")
            }


        }


    }

    private fun show(message: String) {
        Snackbar.make(btnVerify, message, Snackbar.LENGTH_LONG).show()
    }
}
