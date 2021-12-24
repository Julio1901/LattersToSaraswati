package com.julio.latterstosaraswati.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phrase_bank")
class PhraseBankEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "writer_user")
    val writerUser : String,
    @ColumnInfo(name = "creation_date")
    val creationDate : String,
    val phrase : String,
    val phraseOrigin : String
    )