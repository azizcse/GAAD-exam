package com.w3engineers.testkt.data

import android.content.Context
import com.w3engineers.testkt.user.UserViewModelFactory

object Injector {
    fun provideUserDao(context: Context): UserDao {
        val appDatabase = AppDatabase.getInstance(context)
        return appDatabase.getUserDao()
    }

    fun provideViewModelFactory(context: Context): UserViewModelFactory {
        val dataSource = provideUserDao(context)
        return UserViewModelFactory(dataSource)
    }
}