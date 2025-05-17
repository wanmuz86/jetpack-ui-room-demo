package com.muzaffar.noteapproom.repository

import com.muzaffar.noteapproom.daos.NoteDao
import com.muzaffar.noteapproom.models.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes();

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}