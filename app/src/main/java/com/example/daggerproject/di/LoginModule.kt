package com.example.daggerproject.di

import android.content.Context
import android.content.SharedPreferences
import com.example.daggerproject.*
import com.example.daggerproject.data.PreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

/**
 * MODULE
 * Class that defines how the object graph is structured
 * Define our dependencies for the object graph
 */
@Module(
    injects = [LoginActivity::class, RegisterActivity::class]
)
class LoginModule(private val context : Context) {

    @Provides
    fun providesLoginPresenter(preferencesRepository: PreferencesRepository) : LoginContract.Presenter {
        return LoginPresenter(preferencesRepository)
    }

    @Provides
    fun providesRegisterPresenter(preferencesRepository: PreferencesRepository) : RegisterContract.Presenter {
        return RegisterPresenter(preferencesRepository)
    }

    @Provides
    fun providesSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("login preferences", Context.MODE_PRIVATE)
    }

    //Qualifier example
    @AutoPass
    @Provides
    fun providesAutoPassString() : String {
        return "Autopass"
    }

    //Qualifier example
    @Clear
    @Provides
    fun providesClearString() : String {
        return "Clear"
    }
}

//Qualifier example
@Qualifier
annotation class AutoPass

@Qualifier
annotation class Clear