package com.mywings.photographpassword.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mywings.photographpassword.User
import android.R.attr.password


class DatabaseHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    private lateinit var database: SQLiteDatabase


    init {
        database = writableDatabase
    }

    companion object {
        const val CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS USER(id INTEGER PRIMARY KEY AUTOINCREMENT, name text, phone text,email text UNIQUE, dob text,password text,color text)"
        const val USER = "USER"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    public fun insertUser(user: User): Long {

        val contentValue = ContentValues()

        contentValue.put("name", user.name)

        contentValue.put("phone", user.phone)

        contentValue.put("email", user.email)

        contentValue.put("dob", user.dob)

        contentValue.put("password", user.password)

        contentValue.put("color", user.color)

        return database.insertOrThrow(USER, null, contentValue)

        return 0
    }

    public fun getUsers(email: String): User? {
        val args = arrayOf<String>(email)
        val cursor = database.query(USER, null, "email=?", args, null, null, null)
        if (null != cursor) {
            cursor.moveToFirst()
            var user: User? = User(0,"","","","","","")
            user!!.id = cursor.getInt(0)
            user.name = cursor.getString(1)
            user.phone = cursor.getString(2)
            user.email = cursor.getString(3)
            user.dob = cursor.getString(4)
            user.password = cursor.getString(5)
            user.color = cursor.getString(6)
            return user
        }
        return null
    }
}