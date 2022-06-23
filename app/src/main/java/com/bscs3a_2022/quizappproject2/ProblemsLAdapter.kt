package com.bscs3a_2022.quizappproject2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemListCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2

class ProblemsLAdapter(private val clickListener: ProblemItemListener2)
    : ListAdapter<Problems2, ProblemsLAdapter.ViewHolder>(ProblemListDiffCallback2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: QuizProblemListCardBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Problems2, clickListener: ProblemItemListener2) {
            binding.problem2 = item
            binding.clickListener2 = clickListener
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

class ProblemListDiffCallback2 : DiffUtil.ItemCallback<Problems2>() {
    override fun areItemsTheSame(oldItem: Problems2, newItem: Problems2): Boolean {
        return oldItem.problemId == newItem.problemId
    }

    override fun areContentsTheSame(oldItem: Problems2, newItem: Problems2): Boolean {
        return oldItem == newItem
    }
}

class ProblemItemListener2(val clickListener: (Problems: Long) -> Unit) {
    fun onClick(problem: Problems2) = clickListener(problem.problemId)
}