package com.example.noteapp.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

     val repository:NoteRepository
     val notes:LiveData<List<Note>>
     init {
         val dao = NoteDatabase.getDatabase(application).getNotesDao()
         repository = NoteRepository(dao)
         notes = repository.allNotes
     }

    fun insert(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun update(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun delete(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

}
