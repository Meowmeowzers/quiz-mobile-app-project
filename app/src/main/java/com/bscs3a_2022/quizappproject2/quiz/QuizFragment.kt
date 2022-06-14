package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.databinding.FragmentQuizBinding
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizViewModelFactory

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
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
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz"

        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner

        binding.quizViewModel = viewModel
        val adapter = QuizListAdapter()
        binding.quizitemlayout.quizRecycler.adapter = adapter

        viewModel.quizList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}