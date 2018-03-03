package com.mywings.photographpassword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.mywings.photographpassword.database.DatabaseHelper
import com.mywings.photographpassword.model.SendOtp
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity(), OnVerifyOtpListener {


    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        database = DatabaseHelper(this, "graphicpass", null, 1)
        btnRegister.setOnClickListener {
            if (validate()) {
                val user = User(0, txtName.text.toString(), txtPhoneNumber.text.toString(), txtEmail.text.toString(),
                        txtDateOfBirth.text.toString(), txtPassword.text.toString(), spnColors.selectedItem.toString())
                if (database.insertUser(user) > 0) {
                    //show("User created successfully")

                    val exeVerify = SendOtpAsync()
                    exeVerify.setVerifyListener(this@RegistrationActivity, SendOtp("", txtPhoneNumber.text.toString()))

                } else {
                    show("Something went wrong")
                }
            } else {
                show("All fields(*) mandatory")
            }
        }
        spnColors.onItemSelectedListener = colorSelected

    }


    private fun validate(): Boolean {
        if (txtName.text.isNotEmpty() && txtEmail.text.isNotEmpty() && txtPhoneNumber.text.isNotEmpty() && txtDateOfBirth.text.isNotEmpty() && txtPassword.text.isNotEmpty() && !spnColors.selectedItem.toString().contentEquals("Select color")) {
            return true
        }
        return false
    }

    private val colorSelected = object : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (position != 0) {
                val startIndex = adapterView!!.selectedItem!!.toString().indexOf("(")
                val endIndex = adapterView!!.selectedItem!!.toString().indexOf(")")
                val drawable = Color.parseColor(adapterView!!.selectedItem!!.toString().substring(startIndex + 1, endIndex))
                lblSelectedColor.setBackgroundColor(drawable)
            }
        }
    }

    override fun onVerificationComplete(otp: String, success: String) {
        if (success.contentEquals("sent")) {
            show("User created successfully","")
        }else{
            show("Not able send otp")
        }
    }

    private fun show(message: String,extra:String) {
        Snackbar.make(btnRegister, message, Snackbar.LENGTH_INDEFINITE).setAction("OK", {
            val intent = Intent(this@RegistrationActivity, OTPVerificationActivity::class.java)
            startActivity(intent)
        }).show()
    }

    private fun show(message: String) {
        Snackbar.make(btnRegister, message, Snackbar.LENGTH_INDEFINITE).setAction("OK", {}).show()
    }

}
