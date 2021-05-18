package com.github.csandiego.govtechassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.csandiego.govtechassessment.R
import com.github.csandiego.govtechassessment.databinding.FragmentListingBinding
import com.google.android.material.tabs.TabLayoutMediator

class ListingFragment: Fragment(R.layout.fragment_listing) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListingBinding.inflate(inflater, container, false)
        with(binding.toolbar) {
            inflateMenu(R.menu.menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.new_note -> {
                        findNavController().navigate(R.id.newFragment)
                        true
                    }
                    else -> false
                }
            }
        }
        binding.pager.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment = if (position == 0) AllNotesListingFragment() else FavoriteNotesListingFragment()
        }
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) "All Notes" else "Favorites"
        }.attach()
        return binding.root
    }
}