package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeListCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
//
//class QuizTakeAdapter (val clickListener: QuizSetTakeItemListener)
//    : ListAdapter<QuizSet, QuizTakeListAdapter.ViewHolder>(QuizTakeListDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item, clickListener)
//    }
//
//    class ViewHolder private constructor(val binding: QuizTakeListCardBinding) : RecyclerView.ViewHolder(binding.root){
//
//        fun bind(item: QuizSet, clickListener: QuizSetTakeItemListener) {
//            binding.quiz = item
//            binding.clickListener = clickListener
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = QuizTakeListCardBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//}
//
//class QuizTakeListDiffCallback : DiffUtil.ItemCallback<QuizSet>() {
//    override fun areItemsTheSame(oldItem: QuizSet, newItem: QuizSet): Boolean {
//        return oldItem.quizSetId == newItem.quizSetId
//    }
//
//    override fun areContentsTheSame(oldItem: QuizSet, newItem: QuizSet): Boolean {
//        return oldItem == newItem
//    }
//}
//
//class QuizSetTakeItemListener(val clickListener: (quizSetId: Long) -> Unit) {
//    fun onClick(quiz: QuizSet){
//        clickListener(quiz.quizSetId)
//    }
//}