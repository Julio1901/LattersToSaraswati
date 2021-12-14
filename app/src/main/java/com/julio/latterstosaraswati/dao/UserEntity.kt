package com.julio.latterstosaraswati.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_name")
    val userName : String,
    val email : String,
    val password : String
    )