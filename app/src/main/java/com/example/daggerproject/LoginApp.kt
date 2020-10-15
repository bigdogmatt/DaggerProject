package com.example.daggerproject

import android.app.Application
import com.example.daggerproject.di.LoginObjectGraph

//Want to create an application class to pass around to prevent memory leaks
class LoginApp : Application() {
    override fun onCreate() {
        LoginObjectGraph.create(this)
        super.onCreate()
    }
}