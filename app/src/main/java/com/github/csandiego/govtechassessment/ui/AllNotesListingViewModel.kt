package com.github.csandiego.govtechassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.csandiego.govtechassessment.dao.NoteDao
import com.github.csandiego.govtechassessment.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNotesListingViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {

    val notes = dao.getAll()

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            dao.update(note.copy().apply {
                isFavorite = note.isFavorite != true
            })
        }
    }
}