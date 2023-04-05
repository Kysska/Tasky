package com.example.tasky.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasky.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("UPDATE notes_table Set `title` = :title, `desc` = :desc WHERE `id` = :id")
    suspend fun update(id: Int?, title: String?, desc: String?)

}