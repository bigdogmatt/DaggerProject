package com.example.daggerproject

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class MainPresenter : MvpBasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun attachView(view: MainContract.View) {
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