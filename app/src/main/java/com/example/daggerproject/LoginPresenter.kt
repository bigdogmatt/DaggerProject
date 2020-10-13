package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

//middleman between data(repo) and view
class LoginPresenter(
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