package com.mywings.photographpassword

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        if (intent.hasExtra("email")) {
            lblEmail.setText(intent.getStringExtra("email"))
        }
    }

    private fun show(message: String) {
        Snackbar.make(btnLogin, message, Snackbar.LENGTH_LONG).setAction("OK", {}).show()
    }
}
