package com.example.daggerproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class LoginActivity : MvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    val loginButton = findViewById<Button>(R.id.buttonLogin)
    val registerButton = findViewById<Button>(R.id.buttonRegister)
    val emailEditText = findViewById<EditText>(R.id.editTextEmailAddress)
    val passwordEditText = findViewById<EditText>(R.id.editTextPassword)

    loginButton.setOnClickListener {
      val email = emailEditText.text.toString()
      val password = passwordEditText.text.toString()

      val preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)

      val userDetails = preferences.getString(email + password + "data", "Username or password is incorrect.")
      val editor = preferences.edit()
      editor.putString("display", userDetails)
      editor.apply()

      val detailsScreen = Intent(this, MainActivity::class.java)
      startActivity(detailsScreen)
    }

    registerButton.setOnClickListener {
      val registerScreen = Intent(this, RegisterActivity::class.java)
      startActivity(registerScreen)
    }
  }

  override fun createPresenter(): LoginContract.Presenter = LoginPresenter()
}
