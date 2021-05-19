package com.github.csandiego.govtechassessment.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.csandiego.govtechassessment.data.Note
import com.github.csandiego.govtechassessment.databinding.ListHeaderBinding
import com.github.csandiego.govtechassessment.databinding.ListItemBinding
import java.text.SimpleDateFormat

class NoteListAdapter2(private val callback: (note: Note) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ListItem>()

    @SuppressLint("SimpleDateFormat")
    fun submitList(notes: List<Note>) {
        val map = mutableMapOf<String, MutableList<Note>>()
        notes.forEach {
            val key = SimpleDateFormat("yyyy-MM-dd").format(it.createdAt!!)
            if (!map.containsKey(key)) {
                map[key] = mutableListOf()
            }
            map[key]!!.add(it)
        }
        items.clear()
        map.keys.sortedDescending().forEach {
            items.add(ListHeader(SimpleDateFormat("dd MMM yyyy").format(map[it]!!.first().createdAt!!)))
            items.addAll(map[it]!!.map { note -> ListNote(note) })
        }
        notifyDataSetChanged()
    }

    abstract class ListItem {

        abstract fun getType(): Int
    }

    class ListHeader(val date: String) : ListItem() {

        override fun getType(): Int = 0
    }

    class ListNote(val note: Note) : ListItem() {

        override fun getType(): Int = 1
    }

    class ListHeaderViewHolder(val binding: ListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ListNoteViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].getType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 0)
            ListHeaderViewHolder(
                ListHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else
            ListNoteViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            (holder as ListHeaderViewHolder).binding.textViewDate.text =
                (items[position] as ListHeader).date
        } else {
            val note = (items[position] as ListNote).note
            with((holder as ListNoteViewHolder).binding) {
                textViewTitle.text = note.title
                textViewDescription.text = note.description
                imageViewFavorite.setImageResource(if (note.isFavorite == true) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
                imageViewFavorite.setOnClickListener {
                    callback(note)
                }
            }
        }
    }
}