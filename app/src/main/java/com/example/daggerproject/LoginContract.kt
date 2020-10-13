package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface LoginContract {

  interface View : MvpView {
    fun onUserDoesNotExist()
    fun onIncorrectPassword()
    fun onLoginSuccessful()
  }

  interface Presenter : MvpPresenter<View> {
    fun login(username : String, password : String)
  }
}