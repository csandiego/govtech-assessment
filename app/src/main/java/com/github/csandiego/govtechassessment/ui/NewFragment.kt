package com.github.csandiego.govtechassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.csandiego.govtechassessment.databinding.FragmentNewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewFragment : Fragment() {

    private val viewModel by viewModels<NewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.created.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.creationHandled()
                findNavController().navigateUp()
            }
        }
        val binding = FragmentNewBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}