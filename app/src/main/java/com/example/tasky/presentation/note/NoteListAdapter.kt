package com.example.tasky.presentation.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasky.R
import com.example.tasky.data.Note
import com.example.tasky.presentation.fragments.NoteFragment
import java.security.AccessController.getContext

class NoteListAdapter(private val context: NoteFragment, val listener: NoteFragment) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteListAdapter.NoteViewHolder, position: Int) {
        val currentNote = NoteList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.desc.text = currentNote.desc
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NoteList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }


    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newList : List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        NoteList.clear()
        NoteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){
        NoteList.clear()

        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.desc?.lowercase()?.contains(search.lowercase()) == true){
                NoteList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.note_card)
        val title = itemView.findViewById<TextView>(R.id.note_title)
        val desc = itemView.findViewById<TextView>(R.id.note_desc)
        val date = itemView.findViewById<TextView>(R.id.note_date)
    }

    interface NoteClickListener{
        fun onItemClicked(note:Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }
}