package com.example.daggerproject.data

import android.content.SharedPreferences
import com.example.daggerproject.di.AutoPass
import com.example.daggerproject.di.Clear
import javax.inject.Inject
import javax.inject.Singleton

//Constructor injection
@Singleton
class PreferencesRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @AutoPass private val autoPassString: String,
    @Clear private val clearString: String
) {

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
        if (username == autoPassString) {
            println("TAG_ You autopassed")
        }
        if (username == clearString) {
            println("TAG_ You cleared the values")
        }
        val key = sharedPreferences.getString(username, null)
        return !key.isNullOrBlank()
    }
}