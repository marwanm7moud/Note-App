package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.data.Note
import com.example.noteapp.data.NotesViewModel
import com.example.noteapp.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.Date

class addEditNoteActivity : AppCompatActivity() {

    lateinit var titleEditText:EditText
    lateinit var descriptionEditText:EditText
    lateinit var btn:Button
    var noteId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        titleEditText = findViewById(R.id.title)
        descriptionEditText = findViewById(R.id.Description)
        btn = findViewById(R.id.button)

        val viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)


        val notetype = intent.getStringExtra("NoteType")

        if(notetype =="Edit"){
            var notetitle =intent.getStringExtra("NoteTitle").toString()
            var noteDescriptoin=intent.getStringExtra("NoteDescription").toString()
            noteId =intent.getIntExtra("NoteId",-1).toString().toInt()
            btn.text ="Update"
            titleEditText.setText(notetitle)
            descriptionEditText.setText(noteDescriptoin)
        }else{
            btn.text = "Save"
        }

        btn.setOnClickListener {
            val newTitle = titleEditText.text.toString()
            val newDescription = descriptionEditText.text.toString()

            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val current = formatter.format(Date())

            if(notetype =="Edit"){
                if (newTitle.isNotEmpty() &&newDescription.isNotEmpty())
                {
                    val newnote = Note(newTitle , newDescription , current)
                    newnote.id = noteId
                    viewModel.update(newnote)
                    Toast.makeText(this , "Note Updated" , Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                if (newTitle.isNotEmpty() &&newDescription.isNotEmpty())
                {
                    viewModel.insert(Note(newTitle , newDescription , current))
                    Toast.makeText(this , "Note Added" , Toast.LENGTH_SHORT)
                        .show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()

        }






    }
}