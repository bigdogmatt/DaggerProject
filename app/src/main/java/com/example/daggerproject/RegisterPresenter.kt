package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class RegisterPresenter(
    private val preferencesRepository: PreferencesRepository
) : MvpBasePresenter<RegisterContract.View>(), RegisterContract.Presenter{

    override fun isValidPassword(password : String) : Boolean {
        return password.length >= 5
    }

    override fun register(email: String, password: String) = ifViewAttached { view ->
        if(preferencesRepository.register(email, password)) {
            view.onRegisterSuccessful()
        }
        else {
            view.onRegisterUnsuccessful()
        }
    }

}