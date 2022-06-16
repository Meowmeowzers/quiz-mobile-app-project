package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemsBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase


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
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz"

        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
//        val viewModelFactory = QuizListViewModelFactory(dataSource, application)
//        val viewModel =
//            ViewModelProvider(this, viewModelFactory)[QuizListViewModel::class.java]
//        binding.lifecycleOwner = viewLifecycleOwner

//        binding.quizViewModel = viewModel
//        val adapter = QuizListAdapter()
//        binding.quizListRecycler.adapter = adapter

//        viewModel.quizList.observe(viewLifecycleOwner) {
//            it?.let {
//                adapter.submitList(it)
//            }
//        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}