package com.example.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notestable")
data class Note(

    @ColumnInfo(name = "title")val noteTitle:String,
    @ColumnInfo(name = "description")val noteDescription:String,
    @ColumnInfo(name = "timestamp")val noteTimeStamp:String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}