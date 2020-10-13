package com.example.daggerproject.data

import android.content.SharedPreferences

class PreferencesRepository(private val sharedPreferences: SharedPreferences) {

    fun login(username : String, password : String) : Boolean {
        val exists = exists(username)

        if(exists) {
            return sharedPreferences.getString(username, null) == password
        }
        else {
            return false
        }
    }

    fun register(username : String, password : String) : Boolean {
        val exists = exists(username)

        if(exists) {
            return false
        }
        else {
            val editor = sharedPreferences.edit()
            editor.putString(username, password)
            editor.apply()
            return true
        }
    }

    fun exists(username : String) : Boolean {
        val key = sharedPreferences.getString(username, null)
        return !key.isNullOrBlank()
    }
}