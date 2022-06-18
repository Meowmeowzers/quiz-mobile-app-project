package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizListCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

class QuizListAdapter(val clickListener: QuizSetListener)
    : ListAdapter<QuizSet, QuizListAdapter.ViewHolder>(QuizListDiffCallback()) {

    var itemId = 0L
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: QuizListCardBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: QuizSet, clickListener: QuizSetListener) {
            binding.quiz = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizListCardBinding.inflate(layoutInflater, parent, false)
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

class QuizSetListener(val clickListener: (quizSetId: Long) -> Unit) {
    fun onClick(quiz: QuizSet){
        clickListener(quiz.quizSetId)
    }
}