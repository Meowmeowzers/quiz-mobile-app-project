package com.bscs3a_2022.quizappproject2.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizItemCardListBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizSet

class QuizListAdapter: ListAdapter<QuizSet,QuizListAdapter.ViewHolder>(QuizListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: QuizItemCardListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: QuizSet) {
            binding.quiz = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizItemCardListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class QuizListDiffCallback : DiffUtil.ItemCallback<QuizSet>() {
    override fun areItemsTheSame(oldItem: QuizSet, newItem: QuizSet): Boolean {
        return oldItem.quizSetId == newItem.quizSetId
    }

    override fun areContentsTheSame(oldItem: QuizSet, newItem: QuizSet): Boolean {
        return oldItem == newItem
    }
}