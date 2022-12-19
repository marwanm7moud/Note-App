package com.example.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM notesTable ORDER BY id ASC")
    fun getNotes():LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun noteInsert(note: Note)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun noteUpdate(note: Note)

    @Delete
    suspend fun noteDelete(note: Note)

}