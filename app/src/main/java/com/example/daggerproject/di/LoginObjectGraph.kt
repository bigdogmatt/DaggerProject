package com.example.daggerproject.di

import android.content.Context
import dagger.ObjectGraph

//Graph of objects that shows dependencies
object LoginObjectGraph {
    private lateinit var objectGraph: ObjectGraph

    fun create(context : Context) {
        this.objectGraph = ObjectGraph.create(LoginModule(context))
    }

    fun inject(any: Any) {
        objectGraph.inject(any)
    }
}