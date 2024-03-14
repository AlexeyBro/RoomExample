package com.example.roomexample

import android.app.Application
import com.example.roomexample.data.Dao
import com.example.roomexample.data.MainDB

class App: Application() {

    val database by lazy {
        MainDB.createDataBase(this)
    }
}