package com.example.noteapp.data

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<Note>> = noteDao.getNotes()

     suspend fun update(note: Note){
        noteDao.noteUpdate(note)
    }
    suspend fun insert(note: Note){
        noteDao.noteInsert(note)
    }
    suspend fun delete(note: Note){
        noteDao.noteDelete(note)
    }


}