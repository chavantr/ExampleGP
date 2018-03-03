package com.mywings.photographpassword

import android.os.AsyncTask
import com.mywings.pay.models.Constants
import com.mywings.pay.models.StreamFunctions
import com.mywings.photographpassword.model.SendOtp
import java.util.*


class SendOtpAsync : AsyncTask<SendOtp, Void, String>() {

    private lateinit var onVerifyListener: OnVerifyOtpListener

    private var generatedOtp: Int = 0

    override fun doInBackground(vararg params: SendOtp?): String {
        var message: String? = null;
        val random = Random()
        generatedOtp = ((100000 + random.nextInt(999999)))
        Constants.CHECK = generatedOtp
        message = "$generatedOtp"
        return StreamFunctions.convertStreamToString(StreamFunctions.getRequest(Constants.URL + params[0]!!.phone + "&message=$message")!!)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onVerifyListener.onVerificationComplete("$generatedOtp", result!!)

    }

    fun setVerifyListener(onVerifyListener: OnVerifyOtpListener, otp: SendOtp) {
        this.onVerifyListener = onVerifyListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, otp)
    }

}