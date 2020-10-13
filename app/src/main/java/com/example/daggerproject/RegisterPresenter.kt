package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class RegisterPresenter : MvpBasePresenter<RegisterContract.View>(), RegisterContract.Presenter{

    override fun isValidPassword(password : String) : Boolean {
        return password.length >= 5
    }

    override fun attachView(view: RegisterContract.View) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

    override fun detachView() {
        super.detachView()
    }

    override fun destroy() {
        super.destroy()
    }
}