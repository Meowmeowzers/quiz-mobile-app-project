package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.R
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.databinding.QuizListBinding
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizListViewModel
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.QuizListViewModelFactory

class QuizListFragment : Fragment() {
    private var _binding: QuizListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz"

        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizListViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizListViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner

        binding.quizViewModel = viewModel
        val adapter = QuizListAdapter()
        binding.quizListRecycler.adapter = adapter

        viewModel.quizList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.addquizitem.setOnClickListener{
            findNavController().navigate(R.id.action_quizListFragment_to_quizCreateFragment)
        }

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