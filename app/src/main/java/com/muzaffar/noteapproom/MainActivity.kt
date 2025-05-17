package com.muzaffar.noteapproom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muzaffar.noteapproom.database.NotesDatabase
import com.muzaffar.noteapproom.repository.NoteRepository
import com.muzaffar.noteapproom.ui.screens.NoteScreen
import com.muzaffar.noteapproom.ui.theme.NoteAppRoomTheme
import com.muzaffar.noteapproom.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Create the instance of db, repository, viewModel
                    val database = NotesDatabase.getDatabase(applicationContext)
                    val repository = NoteRepository(database.noteDao())
                    val viewModel = NoteViewModel(repository)

                    NoteScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppRoomTheme {

    }
}