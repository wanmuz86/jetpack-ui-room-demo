package com.muzaffar.noteapproom.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muzaffar.noteapproom.models.Note
import kotlinx.coroutines.flow.Flow

// To indicate this is Dao
// which will define the methods to be used to access the db
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(Note: Note)

    @Delete
    suspend fun deleteNote(note:Note)

    // FLow is a stream of data
    // that can be collected in real time (coroutine)
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>
}