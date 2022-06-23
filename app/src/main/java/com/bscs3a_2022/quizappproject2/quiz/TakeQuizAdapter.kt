package com.bscs3a_2022.quizappproject2.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.FragmentTakeQuizCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import kotlin.coroutines.coroutineContext

class TakeQuizAdapter (val clickListener: TakeQuizListener)
    : ListAdapter<Problems2, TakeQuizAdapter.ViewHolder>(TakeQuizDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: FragmentTakeQuizCardBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Problems2, clickListener: TakeQuizListener) {
            binding.problem = item
            binding.takeQuizDescription.text = item.description
            binding.answerChoice1.text = item.choice1
            binding.answerChoice2.text = item.choice2
            binding.answerChoice3.text = item.choice3
            binding.answerChoice4.text = item.choice4
            binding.clicklistener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentTakeQuizCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TakeQuizDiffCallback : DiffUtil.ItemCallback<Problems2>() {
    override fun areItemsTheSame(oldItem: Problems2, newItem: Problems2): Boolean {
        return oldItem.problemId == newItem.problemId
    }

    override fun areContentsTheSame(oldItem: Problems2, newItem: Problems2): Boolean {
        return oldItem == newItem
    }
}

class TakeQuizListener(val clickListener: (Problem :Problems2, number: Int) -> Unit) {
    fun onClick(item: Problems2, num: Int){
        clickListener(item,num)
    }
}