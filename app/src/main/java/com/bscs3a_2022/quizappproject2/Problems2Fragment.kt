package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.FragmentProblems2Binding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class Problems2Fragment : Fragment() {
    private var _binding: FragmentProblems2Binding? = null
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
        _binding = FragmentProblems2Binding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Create Problem"

        val sharedViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = Problems2ViewModelFactory(dataSource, application, sharedViewModel.id)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[Problems2ViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewmodel = viewModel

        binding.saveChoices.setOnClickListener {
            val description = binding.problem2Description.text.toString()
            val choice1 = binding.editTextChoice1.text.toString()
            val choice2 = binding.editTextChoice2.text.toString()
            val choice3 = binding.editTextChoice3.text.toString()
            val choice4 = binding.editTextChoice4.text.toString()
            viewModel.createProblem2(description, choice1, choice2, choice3, choice4)
            this.findNavController().navigate(
                Problems2FragmentDirections.actionProblems2FragmentToProblemsLFragment()
            )
        }

        binding.radiobuttoncreate1.setOnClickListener{
            binding.radiobuttoncreate2.isChecked = false
            binding.radiobuttoncreate3.isChecked = false
            binding.radiobuttoncreate4.isChecked = false
            viewModel.rightChoice = 1
        }
        binding.radiobuttoncreate2.setOnClickListener{
            binding.radiobuttoncreate1.isChecked = false
            binding.radiobuttoncreate3.isChecked = false
            binding.radiobuttoncreate4.isChecked = false
            viewModel.rightChoice = 2
        }
        binding.radiobuttoncreate3.setOnClickListener{
            binding.radiobuttoncreate1.isChecked = false
            binding.radiobuttoncreate2.isChecked = false
            binding.radiobuttoncreate4.isChecked = false
            viewModel.rightChoice = 3
        }
        binding.radiobuttoncreate4.setOnClickListener{
            binding.radiobuttoncreate1.isChecked = false
            binding.radiobuttoncreate2.isChecked = false
            binding.radiobuttoncreate3.isChecked = false
            viewModel.rightChoice = 4
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}