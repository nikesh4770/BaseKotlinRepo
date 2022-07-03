package com.app.recipe.db.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun observeAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE userId IN (:userIds)")
    fun observeAllByIds(userIds: IntArray): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE firstName LIKE :first AND lastName LIKE :last LIMIT 1")
    fun observeFindByName(first: String, last: String): LiveData<User>
}