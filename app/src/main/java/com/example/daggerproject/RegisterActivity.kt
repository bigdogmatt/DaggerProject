package com.example.daggerproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class RegisterActivity : MvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registration)

        setupViews()
    }

    private fun setupViews() {
        val presenter = createPresenter()

        val userName = findViewById<EditText>(R.id.editTextNewName)
        val password = findViewById<EditText>(R.id.editTextNewPassword)
        val email = findViewById<EditText>(R.id.editTextNewEmail)
        val error = findViewById<TextView>(R.id.textViewError)
        val registerButton = findViewById<Button>(R.id.buttonNewRegistration)

        registerButton.setOnClickListener {
            val preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)

            val newUser = userName.text.toString()
            val newEmail = email.text.toString()
            val newPassword = password.text.toString()

            if(presenter.isValidPassword(newPassword))
            {
                val editor = preferences.edit()

                editor.putString(newEmail + newPassword + "data", "$newUser\n$newEmail")
                editor.apply()

                val loginScreen = Intent(this, LoginActivity::class.java)
                startActivity(loginScreen)
            }
            else {
                error.text = "Password must be at least 5 characters long."
            }

        }
    }

    override fun createPresenter(): RegisterContract.Presenter = RegisterPresenter()
}