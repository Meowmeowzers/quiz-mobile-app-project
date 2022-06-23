package com.bscs3a_2022.quizappproject2.quiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeBinding
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeChoiceItemBinding
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeProblemCardBinding
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems


class QuizTakeAdapter(adapter2: QuizTakeChoicesAdapter)
    : ListAdapter<Problems, QuizTakeAdapter.ViewHolder>(QuizTakeProblemListDiffCallback()) {
    var position:Int = 0
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

    class ViewHolder private constructor(val binding: QuizTakeChoiceItemBinding, val binding2: QuizTakeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Choices, clickListener: QuizTakeChoiceListener) {

            binding.choice = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuizTakeChoiceItemBinding.inflate(layoutInflater, parent, false)
                val binding2 = QuizTakeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, binding2)
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
//class CountryStateSectionedAdapter(var countryClickedListener: CountryClickedListener, var countryStateModelList
//:MutableList<ExpandableCountryModel>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private var isFirstItemExpanded : Boolean = true
//    private var actionLock = false
//    lateinit var countryName:String
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when(viewType) {
//            ExpandableCountryModel.PARENT -> {CountryStateParentViewHolder(LayoutInflater.from(parent.context).inflate(
//                R.layout.expandable_parent_item, parent, false))}
//
//            ExpandableCountryModel.CHILD -> { CountryStateChildViewHolder(LayoutInflater.from(parent.context).inflate(
//                R.layout.expandable_child_item, parent, false))  }
//
//            else -> {CountryStateParentViewHolder(LayoutInflater.from(parent.context).inflate(
//                R.layout.expandable_parent_item, parent, false))}
//        }
//    }
//
//    override fun getItemCount(): Int = countryStateModelList.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val row = countryStateModelList[position]
//        when(row.type){
//            ExpandableCountryModel.PARENT -> {
//                (holder as CountryStateParentViewHolder).countryName.text = row.countryParent.country
//                countryName = row.countryParent.country
//                holder.closeImage.visibility = View.GONE
//                holder.upArrowImg.visibility = View.GONE
//
//            }
//            ExpandableCountryModel.CHILD -> {
//                (holder as CountryStateChildViewHolder).stateName.text = row.countryChild.name
//                holder.capitalImage.text = row.countryChild.capital
//                countryName?.let {
//                    holder.layout.tag = it
//                }
//                holder.stateName.tag = row.countryChild
//                holder.layout.setOnClickListener {
//                    var countryInfo =   holder.stateName.tag
//                    countryClickedListener.onItemClick(holder.layout.tag.toString(),
//                        countryInfo as StateCapital.Country.State
//                    )
//                }
//            }
//        }
//
//    }
//
//
//    override fun getItemViewType(position: Int): Int = countryStateModelList[position].type
//
//    class CountryStateParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        internal var layout = itemView.country_item_parent_container
//        internal var countryName : TextView = itemView.country_name
//        internal var closeImage = itemView.close_arrow
//        internal var upArrowImg = itemView.up_arrow
//
//    }
//
//    class CountryStateChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        internal var layout = itemView.country_item_child_container
//        internal var stateName : TextView = itemView.state_name
//        internal var capitalImage = itemView.capital_name
//
//    }
//}

