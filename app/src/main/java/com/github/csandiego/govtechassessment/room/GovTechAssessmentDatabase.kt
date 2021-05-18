package com.github.csandiego.govtechassessment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.csandiego.govtechassessment.dao.NoteDao
import com.github.csandiego.govtechassessment.data.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GovTechAssessmentDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}