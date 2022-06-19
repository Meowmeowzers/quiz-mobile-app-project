package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemsBinding
import com.bscs3a_2022.quizappproject2.quiz.adapters.ProblemItemListener
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizProblemListAdapter
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizProblemListViewModelFactory
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizProblemsListViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel


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

        val sharedViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizProblemListViewModelFactory(dataSource, application, sharedViewModel.id)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizProblemsListViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.problemsListViewModel = viewModel

        val adapter = QuizProblemListAdapter(ProblemItemListener { problem: Long ->
            sharedViewModel.setNewProblemId(problem)
            Toast.makeText(context, sharedViewModel.problemId.toString(), Toast.LENGTH_SHORT).show()
            viewModel.onProblemItemClicked(problem)
        })

        binding.quizProblemsRecycler.adapter = adapter
        viewModel.selectedId = sharedViewModel.id

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
//            Toast.makeText(context, sharedViewModel.pr.toString(), Toast.LENGTH_SHORT).show()
            val problemDescription = binding.editTextCreateProblemDescription.text.toString()
            viewModel.createProblem(sharedViewModel.id ,problemDescription)
//            findNavController().navigate(R.id.action_quizCreateFragment_to_quizListFragment)
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}