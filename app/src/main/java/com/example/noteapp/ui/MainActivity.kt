package com.example.noteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.CustomRecyclerViewAdapter
import com.example.noteapp.R
import com.example.noteapp.addEditNoteActivity
import com.example.noteapp.data.Note
import com.example.noteapp.data.NotesViewModel
import com.example.noteapp.onClickInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , onClickInterface {

    lateinit var recyclerView:RecyclerView
    lateinit var fab :FloatingActionButton
    lateinit var viewmodel:NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomRecyclerViewAdapter(this)
        recyclerView.adapter = adapter

        viewmodel = ViewModelProvider(this ,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewmodel.notes.observe(this , Observer{notesList:List<Note>->
            notesList.let {
                adapter.updateList(it)
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity , addEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }



    }

    override fun deleteNote(note: Note) {
        viewmodel.delete(note)
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this , addEditNoteActivity::class.java)

        intent.putExtra("NoteTitle" , note.noteTitle)
        intent.putExtra("NoteDescription" , note.noteDescription)
        intent.putExtra("NoteId" , note.id)
        intent.putExtra("NoteType" , "Edit")

        startActivity(intent)
        this.finish()
    }
}