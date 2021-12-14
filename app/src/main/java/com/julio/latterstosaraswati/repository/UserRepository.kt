package com.julio.latterstosaraswati.repository

import android.content.Context
import android.util.Log
import com.julio.latterstosaraswati.dao.DataBaseLattersToSaraswati
import com.julio.latterstosaraswati.dao.UserEntity

class UserRepository (context: Context) {

    val dbInstance = DataBaseLattersToSaraswati.getDatabaseInstance(context)
    val daoInstance = dbInstance.dao()


    suspend fun registerNewUser(newUser : UserEntity) : Long{

        try {
              return daoInstance.registerUser(newUser)
        }catch (e : Exception){
            Log.d("Registration error", e.toString())
            return 0
        }
    }

    suspend fun getUserInDb(name : String) : UserEntity?{
        try {
            return daoInstance.getUserInDb(name)
        }catch (e: Exception){
            Log.d("Impossible to find user", e.toString())
            return null
        }
    }

}