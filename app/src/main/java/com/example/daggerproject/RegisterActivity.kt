package com.example.daggerproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.daggerproject.di.LoginObjectGraph
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import javax.inject.Inject

class RegisterActivity : MvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View {

    //Field injection
    @Inject lateinit var registerPresenter : RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        LoginObjectGraph.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        setupViews()
    }

    private fun setupViews() {

        val password = findViewById<EditText>(R.id.editTextNewPassword)
        val email = findViewById<EditText>(R.id.editTextNewEmail)
        val registerButton = findViewById<Button>(R.id.buttonNewRegistration)

        registerButton.setOnClickListener {
            val newEmail = email.text.toString()
            val newPassword = password.text.toString()

            registerPresenter.register(newEmail, newPassword)

            val loginScreen = Intent(this, LoginActivity::class.java)
            startActivity(loginScreen)
        }
    }

    override fun createPresenter(): RegisterContract.Presenter = registerPresenter

    override fun onRegisterSuccessful() {
        Toast.makeText(this, "User successfully registered!", Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterUnsuccessful() {
        Toast.makeText(this, "Registration failed: user already exists or your password is too short.", Toast.LENGTH_SHORT).show()
    }
}