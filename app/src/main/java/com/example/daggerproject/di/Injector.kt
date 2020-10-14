package com.example.daggerproject.di

import android.content.Context
import com.example.daggerproject.LoginContract
import com.example.daggerproject.LoginPresenter
import com.example.daggerproject.RegisterContract
import com.example.daggerproject.RegisterPresenter
import com.example.daggerproject.data.PreferencesRepository

class Injector(private val context : Context) {

    fun providesLoginPresenter() : LoginContract.Presenter {
        val repository = providesPreferencesRepository()
        return LoginPresenter(repository)
    }

    fun providesRegisterPresenter() : RegisterContract.Presenter {
        val repository = providesPreferencesRepository()
        return RegisterPresenter(repository)
    }

    fun providesPreferencesRepository() : PreferencesRepository {
        val sharedPreferences = context.getSharedPreferences("login preferences", Context.MODE_PRIVATE)
        return PreferencesRepository(sharedPreferences)
    }

}