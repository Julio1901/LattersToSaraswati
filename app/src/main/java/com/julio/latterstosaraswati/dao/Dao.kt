package com.julio.latterstosaraswati.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    //TODO: Make query's SQL here

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserEntity) : Long

    @Query("SELECT * FROM user_table WHERE user_name like:userName")
    suspend fun getUserInDb(userName : String) : UserEntity

}