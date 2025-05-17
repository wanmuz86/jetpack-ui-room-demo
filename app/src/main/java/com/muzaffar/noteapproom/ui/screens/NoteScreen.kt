package com.muzaffar.noteapproom.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.muzaffar.noteapproom.ui.components.NoteItem
import com.muzaffar.noteapproom.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(viewModel: NoteViewModel, modifier: Modifier) {
    // Listen to the notes from ViewModel
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    // Scope untuk run asynchronous pracess in background (refer to snackbar example)
    val scope = rememberCoroutineScope()

    // Nilai textfields
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Column(
      modifier = modifier.fillMaxSize().padding(8.dp)
    ){
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.addNote(title.text, description.text)
            title = TextFieldValue("")
            description = TextFieldValue("")
        }) {
            Text("Add new item")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(notes.value) { note ->
                NoteItem(note = note, onDelete = {
                    scope.launch {
                        viewModel.deleteNote(note)
                    }

                })
            }
        }
    }

}