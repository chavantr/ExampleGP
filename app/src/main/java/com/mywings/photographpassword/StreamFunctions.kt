package com.mywings.pay.models

import org.json.JSONObject
import java.io.DataOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URI
import java.nio.charset.Charset


class StreamFunctions {

    companion object {
        fun postRequest(url: String, request: JSONObject): InputStream {
            val httpUrlConnection = getHttpUrlConnection(url)
            httpUrlConnection.requestMethod = Constants.POST
            val dataOutPutStream = DataOutputStream(httpUrlConnection.outputStream)
            dataOutPutStream.writeBytes(request.toString())
            dataOutPutStream.flush()
            dataOutPutStream.close()
            httpUrlConnection.connect()
            return httpUrlConnection.inputStream
        }

        fun getRequest(url: String): InputStream? {
            val httpUrlConnection = getHttpUrlConnection(url)
            httpUrlConnection.requestMethod = Constants.GET
            httpUrlConnection.connect()
            return httpUrlConnection.inputStream
        }

        private fun getHttpUrlConnection(url: String): HttpURLConnection {
            val uri = URI(url).toURL()
            val httpUrlConnection = uri.openConnection() as HttpURLConnection
            httpUrlConnection.doOutput = true
            httpUrlConnection.doInput = true
            //httpUrlConnection.setRequestProperty("Content-Type", "application/json")
            return httpUrlConnection
        }

        fun convertStreamToString(inputStream: InputStream): String {
            return inputStream.readBytes().toString(Charset.defaultCharset())
        }

    }

}