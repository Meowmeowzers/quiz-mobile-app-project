package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemsBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizProblemListViewModelFactory
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizProblemsListViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModelFactory


class QuizProblemsListFragment: Fragment() {
    private var _binding: QuizProblemsBinding? = null
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
        _binding = QuizProblemsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz Problems"

        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizProblemListViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizProblemsListViewModel::class.java]
        val viewModelFactory2 = ShareViewModelFactory(dataSource, application)
        val shareViewModel =
            ViewModelProvider(this, viewModelFactory2)[ShareViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        binding.problemsListViewModel = viewModel
        val adapter = QuizProblemListAdapter(ProblemItemListener { problem: Long ->
            viewModel.onProblemItemClicked(problem)
        })
        binding.quizProblemsRecycler.adapter = adapter

        viewModel.listofProblems.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.navigateToProblemChoicesDetails.observe(viewLifecycleOwner) { problem ->
            problem?.let {
                this.findNavController().navigate(
                    QuizProblemsListFragmentDirections.actionQuizProblemsListFragmentToQuizProblemChoicesEditFragment(problem))
                viewModel.onProblemItemNavigated()
            }
        }
        binding.addquizproblem.setOnClickListener {
            val problemDescription = binding.editTextCreateProblemDescription.text.toString()
            viewModel.createProblem(shareViewModel.quizId,problemDescription)
//            findNavController().navigate(R.id.action_quizCreateFragment_to_quizListFragment)
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}