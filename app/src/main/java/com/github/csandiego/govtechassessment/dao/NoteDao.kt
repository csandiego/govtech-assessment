package com.github.csandiego.govtechassessment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.csandiego.govtechassessment.data.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE isFavorite = TRUE")
    fun getFavorites(): LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note): Int
}