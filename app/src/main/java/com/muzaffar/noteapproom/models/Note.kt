package com.muzaffar.noteapproom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity - TO specify this is a table
// and the table name is notes
@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title:String,
    val description:String
)