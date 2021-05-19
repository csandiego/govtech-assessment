package com.github.csandiego.govtechassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.csandiego.govtechassessment.dao.NoteDao
import com.github.csandiego.govtechassessment.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {

    var title: String? = ""

    var description: String? = ""

    private val _created = MutableLiveData(false)

    val created: LiveData<Boolean> = _created

    fun creationHandled() {
        _created.value = false
    }

    fun create() {
        viewModelScope.launch {
            dao.insert(Note(0, title, description, false, Date()))
            _created.value = true
        }
    }
}