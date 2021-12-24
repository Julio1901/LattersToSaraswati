package com.julio.latterstosaraswati.repository

import android.content.Context
import android.util.Log
import com.julio.latterstosaraswati.dao.DataBaseLattersToSaraswati
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.dao.PhraseBankEntity
import com.julio.latterstosaraswati.dao.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository (context: Context) {

    val dbInstance = DataBaseLattersToSaraswati.getDatabaseInstance(context)
    val daoInstance = dbInstance.dao()

    //User table
    suspend fun registerNewUser(newUser : UserEntity) : Long {
        return daoInstance.registerUser(newUser)
    }

    suspend fun getUserInDb(name : String) : UserEntity{
            return daoInstance.getUserInDb(name)
    }

    //TODO CREATE A GRATITUDE REPOSITORY CLASS AND REPLACE IT

    //Gratitude Of the day table

    suspend fun registerNewGratitudeOfTheDay(gratitudeRecord : GratitudeOfTheDayEntity) : Long {
        return daoInstance.registerNewGratitudeOfTheDay(gratitudeRecord)
    }

    suspend fun getGratitudeOfTheDayById(id : Int) : GratitudeOfTheDayEntity{
        return daoInstance.getGratitudeRecordById(id)
    }

    fun getAllGratitudeRegisters(): Flow<List<GratitudeOfTheDayEntity>>{
        return daoInstance.getAllGratitudeRegisters()
    }

    //Phrase Bank table

    suspend fun registerNewPhraseInBank(newPhrase : PhraseBankEntity){
        daoInstance.registerNewPhraseInBank(newPhrase)

    }

}