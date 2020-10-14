package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class LoginPresenterTest {

    private val mockRepository = mock<PreferencesRepository>()
    private val presenter = LoginPresenter(mockRepository)
    private val mockView = mock<LoginContract.View>()

    @Before
    fun setUp() {
        presenter.attachView(mockView)
    }

    @Test
    fun testLoginSuccessful() {
        doReturn(true).whenever(mockRepository).exists(any())
        doReturn(true).whenever(mockRepository).login(any(), any())

        presenter.login("username", "password")

        verify(mockRepository).login("username", "password")
        verify(mockRepository).exists("username")
        verify(mockView).onLoginSuccessful()
    }

    @Test
    fun testLoginUserDoesNotExist() {
        doReturn(false).whenever(mockRepository).exists(any())

        presenter.login("username", "password")

        verify(mockRepository).exists("username")
        verify(mockView).onUserDoesNotExist()
    }

    @Test
    fun testLoginIncorrectPassword() {
        doReturn(true).whenever(mockRepository).exists(any())
        doReturn(false).whenever(mockRepository).login(any(), any())

        presenter.login("username", "password")

        verify(mockRepository).login("username", "password")
        verify(mockRepository).exists("username")
        verify(mockView).onIncorrectPassword()
    }
}