package com.example.daggerproject.data

import android.content.SharedPreferences

class PreferencesRepository(private val sharedPreferences: SharedPreferences) {

    fun login(username : String, password : String) : Boolean {
        val exists = exists(username)

        return if(exists) {
            sharedPreferences.getString(username, null) == password
        } else {
            false
        }
    }

    fun register(username : String, password : String) : Boolean {
        val exists = exists(username)

        return if(exists || password.length < 5) {
            false
        } else {
            val editor = sharedPreferences.edit()
            editor.putString(username, password)
            editor.apply()
            true
        }
    }

    fun exists(username : String) : Boolean {
        val key = sharedPreferences.getString(username, null)
        return !key.isNullOrBlank()
    }
}