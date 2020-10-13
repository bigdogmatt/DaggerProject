package com.example.daggerproject

class User(private val email: String, private val password: String) : UserInterface {

  override fun getEmail(): String {
    return email
  }

  override fun getPassword(): String {
    return password
  }

}