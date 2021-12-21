package com.julio.latterstosaraswati.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gratitude_of_the_day")
class GratitudeOfTheDayEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val user : String,
    val day : String,
    @ColumnInfo(name = "highlighted_word")
    val highlightedWord: String,
    @ColumnInfo(name = "record_of_the_day")
    val recordOfTheDay : String,
    val picture : ByteArray
)