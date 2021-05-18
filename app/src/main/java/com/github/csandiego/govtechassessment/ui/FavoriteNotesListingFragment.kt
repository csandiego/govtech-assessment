package com.github.csandiego.govtechassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.csandiego.govtechassessment.data.Note
import com.github.csandiego.govtechassessment.databinding.FragmentListBinding

class FavoriteNotesListingFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root.apply {
            adapter = NoteListAdapter().apply {
                submitList(listOf(Note(1, "Note1"), Note(2, "Note2")))
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}