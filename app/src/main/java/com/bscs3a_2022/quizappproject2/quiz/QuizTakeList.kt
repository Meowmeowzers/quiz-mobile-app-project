package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.R
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeListBinding
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizSetTakeItemListener
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizTakeListAdapter
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.*

class QuizTakeList : Fragment() {
    private var _binding: QuizTakeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizTakeListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Take a Quiz"

        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizTakeListViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizTakeListViewModel::class.java]
        val shareViewModel: ShareViewModel by activityViewModels()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = QuizTakeListAdapter(QuizSetTakeItemListener { id: Long ->
            shareViewModel.setNewId(id)
            viewModel.onQuizItemClicked(id)
        })

        binding.quizTakeListRecycler.adapter = adapter

        viewModel.quizList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.navigateToQuizTake.observe(viewLifecycleOwner) { quiz ->
            quiz?.let {
                this.findNavController().navigate(
                    QuizTakeListDirections.actionQuizTakeListToQuizTakeActive())
                viewModel.onQuizItemDetailNavigated()
                Toast.makeText(context, shareViewModel.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }
//        binding.addquizitem.setOnClickListener{
//            findNavController().navigate(R.id.action_quizListFragment_to_quizCreateFragment)
//        }
//
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu_quiz_list, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}