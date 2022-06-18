package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemListCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems

class QuizProblemListAdapter(private val clickListener: ProblemItemListener)
    : ListAdapter<Problems, QuizProblemListAdapter.ViewHolder>(ProblemListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: QuizProblemListCardBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Problems, clickListener: ProblemItemListener) {
            binding.problem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizProblemListCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProblemListDiffCallback : DiffUtil.ItemCallback<Problems>() {
    override fun areItemsTheSame(oldItem: Problems, newItem: Problems): Boolean {
        return oldItem.problemId == newItem.problemId
    }

    override fun areContentsTheSame(oldItem: Problems, newItem: Problems): Boolean {
        return oldItem == newItem
    }
}

class ProblemItemListener(val clickListener: (Problems: Long) -> Unit) {
    fun onClick(problem: Problems) = clickListener(problem.problemId)
}