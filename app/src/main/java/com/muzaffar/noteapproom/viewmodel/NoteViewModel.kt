package com.muzaffar.noteapproom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzaffar.noteapproom.models.Note
import com.muzaffar.noteapproom.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel(){
    val notes = repository.allNotes

    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            repository.insertNote(Note(title = title, description = description))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }



}