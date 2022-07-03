package com.app.recipe.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    var userName: String,
    val password: String,
    val imgUrl: String,
    val firstName: String?,
    val lastName: String?,

    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null
)