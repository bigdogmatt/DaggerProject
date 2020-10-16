package com.example.daggerproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.daggerproject.di.LoginObjectGraph
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import javax.inject.Inject

class LoginActivity : MvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {

  //Field injection
  @Inject lateinit var loginPresenter : LoginContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    LoginObjectGraph.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    setupViews()
  }

  private fun setupViews() {
    val loginButton = findViewById<Button>(R.id.buttonLogin)
    val registerButton = findViewById<Button>(R.id.buttonRegister)
    val emailEditText = findViewById<EditText>(R.id.editTextEmailAddress)
    val passwordEditText = findViewById<EditText>(R.id.editTextPassword)

    loginButton.setOnClickListener {
      val email = emailEditText.text.toString()
      val password = passwordEditText.text.toString()
      loginPresenter.login(email, password)
    }

    registerButton.setOnClickListener {
      val registerScreen = Intent(this, RegisterActivity::class.java)
      startActivity(registerScreen)
    }
  }

  override fun createPresenter(): LoginContract.Presenter = loginPresenter

  override fun onUserDoesNotExist() {
    Toast.makeText(this, "User does not exist!", Toast.LENGTH_SHORT).show()
  }

  override fun onIncorrectPassword() {
    Toast.makeText(this, "Incorrect password!", Toast.LENGTH_SHORT).show()
  }

  override fun onLoginSuccessful() {
    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
  }
}
