package com.mywings.photographpassword


interface OnVerifyOtpListener {
    fun onVerificationComplete(otp: String, success: String);
}