package com.github.csandiego.govtechassessment.ui

import androidx.lifecycle.ViewModel
import com.github.csandiego.govtechassessment.dao.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteNotesListingViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {

    val notes = dao.getFavorites()
}