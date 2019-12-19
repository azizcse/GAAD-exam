package com.w3engineers.testkt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User): Completable

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: String): Flowable<User>
    /**
     * Delete all users.
     */
    @Query("DELETE FROM user")
    fun deleteAllUsers()
}