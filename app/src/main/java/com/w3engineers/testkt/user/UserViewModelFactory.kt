package com.w3engineers.testkt.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w3engineers.testkt.data.UserDao

class UserViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}