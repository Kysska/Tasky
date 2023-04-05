package com.example.tasky.presentation.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasky.data.Note
import com.example.tasky.data.db.NoteDatabase
import com.example.tasky.data.repository.NoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepositoryImpl

    val allnotes: LiveData<List<Note>>

    init{
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepositoryImpl(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
}