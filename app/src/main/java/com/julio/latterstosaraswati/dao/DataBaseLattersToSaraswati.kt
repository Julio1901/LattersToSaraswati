package com.julio.latterstosaraswati.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [UserEntity::class,PhraseBankEntity::class], exportSchema = true)
abstract class DataBaseLattersToSaraswati : RoomDatabase(){

    abstract fun dao() : Dao


    companion object{
        fun getDatabaseInstance (context: Context) : DataBaseLattersToSaraswati{
            return Room.databaseBuilder(context, DataBaseLattersToSaraswati::class.java, "DataBaseLattersToSaraswati")
                .build()
        }


    }


}