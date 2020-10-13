package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class LoginPresenter : MvpBasePresenter<LoginContract.View>(), LoginContract.Presenter {

  override fun attachView(view: LoginContract.View) {
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