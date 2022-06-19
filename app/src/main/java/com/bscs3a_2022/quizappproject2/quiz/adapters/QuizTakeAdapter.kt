package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeChoiceItemBinding
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeProblemCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems


class QuizTakeAdapter(adapter2: QuizTakeChoicesAdapter)
    : ListAdapter<Problems, QuizTakeAdapter.ViewHolder>(QuizTakeProblemListDiffCallback()) {

    val adapter = adapter2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, adapter)
    }

    class ViewHolder private constructor(
        val binding: QuizTakeProblemCardBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Problems,adapter2: QuizTakeChoicesAdapter) {
            binding.problem = item
            binding.quizTakeChoiceRecycler.adapter = adapter2
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizTakeProblemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class QuizTakeProblemListDiffCallback : DiffUtil.ItemCallback<Problems>() {
    override fun areItemsTheSame(oldItem: Problems, newItem: Problems): Boolean {
        return oldItem.problemId == newItem.problemId
    }

    override fun areContentsTheSame(oldItem: Problems, newItem: Problems): Boolean {
        return oldItem == newItem
    }
}


class QuizTakeChoicesAdapter (val clickListener: QuizTakeChoiceListener)
    : ListAdapter<Choices, QuizTakeChoicesAdapter.ViewHolder>(QuizTakeChoiceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: QuizTakeChoiceItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Choices, clickListener: QuizTakeChoiceListener) {
            binding.choice = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizTakeChoiceItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class QuizTakeChoiceDiffCallback : DiffUtil.ItemCallback<Choices>() {
    override fun areItemsTheSame(oldItem: Choices, newItem: Choices): Boolean {
        return oldItem.choiceId == newItem.choiceId
    }

    override fun areContentsTheSame(oldItem: Choices, newItem: Choices): Boolean {
        return oldItem == newItem
    }
}

class QuizTakeChoiceListener(val clickListener: (choiceId: Long) -> Unit) {
    fun onClick(choice: Choices){
        clickListener(choice.choiceId)
    }
}

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
//class QuizTakeListener(val clickListener: (quizSetId: Long) -> Unit) {
//    fun onClick(quiz: QuizSet){
//        clickListener(quiz.quizSetId)
//    }
//}


//class MainHouseHolder(val binding: QuizTakeProblemCardBinding): RecyclerView.ViewHolder(binding.root)
//
//class MainHouseAdapter(private val context: Context, private val data: List<Problems>):
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun getItemCount(): Int = data.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHouseHolder =
//        MainHouseHolder(QuizTakeProblemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val problem = data[position]
//        val binding = (holder as MainHouseHolder).binding
//
//        binding.quizTakeChoiceRecycler.adapter = MainTagAdapter(problem.)
//        binding.innerRecycler.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//        // process outer recycler view
//    }
//}
//
//class MainTagHolder(val binding: QuizTakeChoiceItemBinding) : RecyclerView.ViewHolder(binding.root)
//
//class MainTagAdapter(private val data: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    var tagData :MutableList<String> = mutableListOf()
//    override fun getItemCount(): Int  {
//        tagData  = data.split(",") as MutableList<String>
//        return tagData.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTagHolder =
//        MainTagHolder(ItemInnerRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val binding = (holder as MainTagHolder).binding
//        binding.houseTag.text = tagData[position]
//    }
//}
