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
import com.bscs3a_2022.quizappproject2.databinding.FragmentTakeQuizBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class TakeQuizFragment : Fragment() {
    private var _binding: FragmentTakeQuizBinding? = null
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
        _binding = FragmentTakeQuizBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz"

        val shareViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = TakeQuizViewModelFactory(dataSource, application, shareViewModel.id)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[TakeQuizViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = TakeQuizAdapter(TakeQuizListener { id: Problems2, num: Int ->
            viewModel.onChoiceClick(id, num)
            }
        )

        binding.takeQuizRecycler.adapter = adapter

        viewModel.problem.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                viewModel.problemCount = adapter.itemCount

            }
        }

        viewModel.click.observe(viewLifecycleOwner) {
            it?.let {
                if(viewModel.answeredCount == viewModel.problemCount) {
                    binding.passAnswers.visibility = View.VISIBLE
                }
                viewModel.onClickAfter()
            }
        }
        binding.passAnswers.setOnClickListener{
            shareViewModel.score = viewModel.score
            shareViewModel.over = viewModel.problemCount
            viewModel.score = 0
            viewModel.problemCount = 0
            this.findNavController().navigate(
                TakeQuizFragmentDirections.actionTakeQuizFragmentToTakeQuizResultFragment())
        }
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
//        inflater.inflate(R.menu.menu_quiz_list, menu)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}