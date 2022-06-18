package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemChoicesEditListCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import timber.log.Timber

class ProblemChoicesEditAdapter(private val clickListener: ChoiceItemListener)
    : ListAdapter<Choices, ProblemChoicesEditAdapter.ViewHolder>(ChoiceListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: QuizProblemChoicesEditListCardBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Choices, clickListener: ChoiceItemListener) {
            binding.choice = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizProblemChoicesEditListCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ChoiceListDiffCallback : DiffUtil.ItemCallback<Choices>() {
    override fun areItemsTheSame(oldItem: Choices, newItem: Choices): Boolean {
        return oldItem.choiceId == newItem.choiceId
    }

    override fun areContentsTheSame(oldItem: Choices, newItem: Choices): Boolean {
        return oldItem == newItem
    }
}

class ChoiceItemListener(val clickListener: (Choice: Long) -> Unit) {
    fun onClick(choice: Choices) {
        Timber.i("db process")
        clickListener(choice.choiceId)
    }
}