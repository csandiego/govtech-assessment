package com.github.csandiego.govtechassessment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.csandiego.govtechassessment.data.Note
import com.github.csandiego.govtechassessment.databinding.ListItemBinding

class NoteListAdapter(private val callback: (note: Note) -> Unit) :
    ListAdapter<Note, NoteListAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        with(holder.binding) {
            textViewTitle.text = note.title
            textViewDescription.text = note.description
            imageViewFavorite.setImageResource(if (note.isFavorite == true) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
            imageViewFavorite.setOnClickListener {
                callback(note)
            }
        }
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    class ItemCallback : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}