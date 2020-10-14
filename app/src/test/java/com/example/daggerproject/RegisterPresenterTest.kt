package com.example.daggerproject

import com.example.daggerproject.data.PreferencesRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class RegisterPresenterTest {

    private val mockRepository = mock<PreferencesRepository>()
    private val presenter = RegisterPresenter(mockRepository)
    private val mockView = mock<RegisterContract.View>()

    @Before
    fun setUp() {
        presenter.attachView(mockView)
    }

    @Test
    fun testRegisterSuccessful() {
        doReturn(true).whenever(mockRepository).register(any(), any())

        presenter.register("username", "password")

        verify(mockRepository).register("username", "password")
        verify(mockView).onRegisterSuccessful()
    }

    @Test
    fun testRegisterUnsuccessful() {
        doReturn(false).whenever(mockRepository).register(any(), any())

        presenter.register("username", "password")

        verify(mockRepository).register("username", "password")
        verify(mockView).onRegisterUnsuccessful()
    }
}