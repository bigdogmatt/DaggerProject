package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface RegisterContract {

    interface View : MvpView {
        fun onRegisterSuccessful()
        fun onRegisterUnsuccessful()
    }

    interface Presenter : MvpPresenter<View> {
        fun register(email : String, password : String)
    }
}