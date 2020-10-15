package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

//Constructor injection
class RegisterPresenter @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : MvpBasePresenter<RegisterContract.View>(), RegisterContract.Presenter{

    override fun register(email: String, password: String) = ifViewAttached { view ->
        if(preferencesRepository.register(email, password)) {
            view.onRegisterSuccessful()
        }
        else {
            view.onRegisterUnsuccessful()
        }
    }

}