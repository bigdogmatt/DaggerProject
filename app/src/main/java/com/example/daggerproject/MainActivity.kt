package com.example.daggerproject

import android.os.Bundle
import android.widget.TextView
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class MainActivity : MvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)

        val preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val display = preferences.getString("display", "")

        val displayInfo = findViewById<TextView>(R.id.textViewName)
        displayInfo.text = display
    }

    override fun createPresenter(): MainContract.Presenter = MainPresenter()
}