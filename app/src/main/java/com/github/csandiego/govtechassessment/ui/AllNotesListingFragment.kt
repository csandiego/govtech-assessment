package com.github.csandiego.govtechassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.csandiego.govtechassessment.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllNotesListingFragment @Inject constructor() : Fragment() {

    private val viewModel by viewModels<AllNotesListingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = NoteListAdapter()
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        val binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}