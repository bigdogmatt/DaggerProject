package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainContract {

    interface View : MvpView {

    }

    interface Presenter : MvpPresenter<View> {

    }
}