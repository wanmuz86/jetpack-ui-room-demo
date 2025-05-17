package com.muzaffar.noteapproom.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muzaffar.noteapproom.daos.NoteDao

abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    // To create a singleton object
    companion object {

        // @Volatile - Writes to this field are immediately made visible to other threads.
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context:Context): NotesDatabase {
            // SIngleton
            // Kalau Instance ada saya akan pulangkan instance
            // Jika tidak, saya akan create instance baru
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}