package com.example.daggerproject.data

import android.content.SharedPreferences
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class PreferencesRepositoryTest {

    private val mockEditor = mock<SharedPreferences.Editor>()
    private val mockSharedPreferences = mock<SharedPreferences>{
        on { edit() } doReturn mockEditor
    }
    private val repository = PreferencesRepository(mockSharedPreferences)

    @Test
    fun testLoginUserDoesNotExist() {
        doReturn(null).whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.login("username", "password")

        verify(mockSharedPreferences).getString("username", null)
        assertThat(actual).isFalse()
    }

    @Test
    fun testLoginUserExistsWithCorrectPassword() {
        doReturn("password").whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.login("username", "password")

        verify(mockSharedPreferences, times(2)).getString("username", null)
        assertThat(actual).isTrue()
    }

    @Test
    fun testLoginUserExistsWithIncorrect() {
        doReturn("not password").whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.login("username", "password")

        verify(mockSharedPreferences, times(2)).getString("username", null)
        assertThat(actual).isFalse()
    }

    @Test
    fun testRegisterUserAlreadyExists() {
        doReturn("username").whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.register("username", "password")

        verify(mockSharedPreferences).getString("username", null)
        assertThat(actual).isFalse()
    }

    @Test
    fun testRegisterUserDoesNotExistButPasswordIsTooShort() {
        doReturn(null).whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.register("username", "pass")

        verify(mockSharedPreferences).getString("username", null)
        assertThat(actual).isFalse()
    }

    @Test
    fun testRegisterUserDoesNotExist() {
        doReturn(null).whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.register("username", "password")

        verify(mockSharedPreferences).getString("username", null)
        verify(mockEditor).putString("username", "password")
        verify(mockEditor).apply()
        assertThat(actual).isTrue()
    }

    @Test
    fun testExistsWhenStringIsNull() {
        val actual = repository.exists("username")

        assertThat(actual).isFalse()
    }

    @Test
    fun testExistsWhenStringIsEmpty() {
        doReturn("").whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.exists("username")

        verify(mockSharedPreferences).getString("username", null)
        assertThat(actual).isFalse()
    }

    @Test
    fun testExistsWhenStringIsNotEmpty() {
        doReturn("username").whenever(mockSharedPreferences).getString(any(), anyOrNull())

        val actual = repository.exists("username")

        verify(mockSharedPreferences).getString("username", null)
        assertThat(actual).isTrue()
    }
}