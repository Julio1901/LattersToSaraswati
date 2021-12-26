package com.julio.latterstosaraswati.dao

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    //TODO: Make query's SQL here

    //  user table querys
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UserEntity) : Long

    @Query("SELECT * FROM user_table WHERE user_name like:userName")
    suspend fun getUserInDb(userName : String) : UserEntity


    //Gratitude of the day querys
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerNewGratitudeOfTheDay(gratitudeRecord : GratitudeOfTheDayEntity) : Long

    @Query("SELECT * FROM gratitude_of_the_day WHERE id like:id")
    suspend fun getGratitudeRecordById(id: Int) : GratitudeOfTheDayEntity

    @Query("SELECT * FROM gratitude_of_the_day  ORDER BY id ASC")
    fun getAllGratitudeRegisters() : Flow<List<GratitudeOfTheDayEntity>>

    @Delete
    suspend fun deleteGratitude(gratitudeRegister : GratitudeOfTheDayEntity)

    @Update
    suspend fun updateGratitude(gratitudeRegister: GratitudeOfTheDayEntity)


    //Phrase Bank querys
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerNewPhraseInBank(newPhrase : PhraseBankEntity)


}