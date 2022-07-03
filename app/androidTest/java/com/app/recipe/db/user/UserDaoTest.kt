package com.app.recipe.db.user

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.app.recipe.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var userDatabase: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // In order to run room database independent for each test and not interfere with multi thread access to database
        // allow it to run on Main thread. Also because we can manipulate a thread delays in testing environment
        userDatabase = Room.inMemoryDatabaseBuilder(
            context,
            UserDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        userDao = userDatabase.userDao()
    }

    @After
    fun tearDown() {
        userDatabase.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val user = User(
            "mahajann",
            "123",
            "image.png",
            "nikesh",
            "Mahajan",
            1
        )

        userDao.insertUser(user)

        val allUsers = userDao.observeAllUsers().getOrAwaitValue()
        assertThat(allUsers).contains(user)
    }

    @Test
    fun insertMultipleUser() = runBlocking {
        val user1 = User("mahajann1", "123", "image1.png", "Nikesh", "Mahajan", 1)
        val user2 = User("mahajann2", "456", "image2.png", "Nikhil", "Mahajan", 2)
        val user3 = User("mahajann3", "789", "image3.png", "Nilesh", "Mahajan", 3)

        userDao.insertAll(user1, user2, user3)

        val allUsers = userDao.observeAllUsers().getOrAwaitValue()
        assertThat(allUsers).hasSize(3)
    }

    @Test
    fun insertSameUser_replaceWithExistingUser() = runBlocking {
        val user = User(
            "mahajann",
            "123",
            "image.png",
            "nikesh",
            "Mahajan",
            1
        )
        userDao.insertUser(user)

        user.userName = "nikesh1"
        userDao.insertUser(user)

        val allUsers = userDao.observeAllUsers().getOrAwaitValue()
        assertThat(allUsers).hasSize(1)
    }

    @Test
    fun deleteUser() = runBlocking {
        val user = User(
            "mahajann",
            "123",
            "image.png",
            "nikesh",
            "Mahajan",
            1
        )

        userDao.insertUser(user)

        val allUsers = userDao.observeAllUsers().getOrAwaitValue()
        assertThat(allUsers).contains(user)

        userDao.deleteUser(user)
        val afterDeletedUsers = userDao.observeAllUsers().getOrAwaitValue()
        assertThat(afterDeletedUsers).doesNotContain(user)
    }
}