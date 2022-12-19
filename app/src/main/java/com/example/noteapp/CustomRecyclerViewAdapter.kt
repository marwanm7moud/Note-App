package com.example.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note

class CustomRecyclerViewAdapter(val onClickInterface :onClickInterface) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    private val allnotes = ArrayList<Note>()


    fun updateList(newList:List<Note>){
        allnotes.clear()
        allnotes.addAll(newList)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent,false)
        return ViewHolder(holder)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = allnotes.get(position).noteTitle
        holder.timeStamp.text = "Latest Update ${allnotes.get(position).noteTimeStamp}"

        holder.deleteImage.setOnClickListener {
            onClickInterface.deleteNote(allnotes.get(position))
        }
        holder.itemView.setOnClickListener {
            onClickInterface.onNoteClick(allnotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allnotes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val timeStamp = itemView.findViewById<TextView>(R.id.timestamp)
        val deleteImage = itemView.findViewById<ImageView>(R.id.deleteImage)
    }


}


interface onClickInterface{

    fun deleteNote(note:Note)

    fun onNoteClick(note:Note)

}