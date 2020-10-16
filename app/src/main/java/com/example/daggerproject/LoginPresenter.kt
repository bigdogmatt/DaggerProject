package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

/**
 * Constructor injection
 * providesLoginPresenter in the module takes precedence so this injection doesn't really do anything
 */
class LoginPresenter @Inject constructor(
  private val preferencesRepository: PreferencesRepository
) : MvpBasePresenter<LoginContract.View>(), LoginContract.Presenter {

  override fun login(username: String, password: String) = ifViewAttached { view ->
    if(!preferencesRepository.exists(username)) {
      view.onUserDoesNotExist()
    }
    else if(preferencesRepository.login(username, password)) {
      view.onLoginSuccessful()
    }
    else {
      view.onIncorrectPassword()
    }
  }

}