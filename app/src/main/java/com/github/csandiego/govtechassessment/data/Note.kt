package com.github.csandiego.govtechassessment.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String?,
    var description: String?,
    var isFavorite: Boolean?,
    var createdAt: Date?
)
