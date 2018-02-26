package com.mywings.photographpassword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.mywings.photographpassword.database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this, "graphicpass", null, 1)

        btnLogin.setOnClickListener {
            if (txtEmail.text.toString().isNotEmpty()) {
                if (databaseHelper.getUsers(txtEmail.text.toString()) != null)
                    startVerificationScreen(txtEmail.text.toString())
                else
                    show("Email does not match, enter valid email")
            } else {
                show("Enter email address")
            }

        }

        btnSignUp.setOnClickListener {
            startRegistrationActivity()
        }

    }

    private fun startRegistrationActivity() {
        val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun startVerificationScreen(email:String) {
        val intent = Intent(this@MainActivity, VerificationActivity::class.java)
        intent.putExtra("email",email)
        startActivity(intent)
    }

    private fun show(message: String) {
        Snackbar.make(btnLogin, message, Snackbar.LENGTH_LONG).setAction("OK", {}).show()
    }
}
