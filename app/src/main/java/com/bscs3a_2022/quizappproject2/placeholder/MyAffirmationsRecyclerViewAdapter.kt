package com.bscs3a_2022.quizappproject2.placeholder

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.bscs3a_2022.quizappproject2.placeholder.PlaceholderContent.PlaceholderItem
import com.bscs3a_2022.quizappproject2.databinding.FragmentAffirmationItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAffirmationsRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyAffirmationsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAffirmationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAffirmationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.affirmationsItemNumber
        val contentView: TextView = binding.affirmationsItemContent

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}