package com.w3engineers.testkt.user

import androidx.lifecycle.ViewModel
import com.w3engineers.testkt.data.User
import com.w3engineers.testkt.data.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(private val dataSource: UserDao) : ViewModel() {

    fun userName(): Flowable<String> {
        return dataSource.getUserById(USER_ID)
            .map { user -> user.name }
    }

    fun updateUserName(userName: String): Completable {
        val user = User(USER_ID, userName)
        return dataSource.addUser(user)
    }

    companion object {
        const val USER_ID = "1"
    }
}