package com.julio.latterstosaraswati.repository

import android.content.Context
import android.util.Log
import com.julio.latterstosaraswati.dao.DataBaseLattersToSaraswati
import com.julio.latterstosaraswati.dao.UserEntity

class UserRepository (context: Context) {

    val dbInstance = DataBaseLattersToSaraswati.getDatabaseInstance(context)
    val daoInstance = dbInstance.dao()


    suspend fun registerNewUser(newUser : UserEntity) : Long {
        return daoInstance.registerUser(newUser)
    }

    suspend fun getUserInDb(name : String) : UserEntity{
            return daoInstance.getUserInDb(name)
    }
}