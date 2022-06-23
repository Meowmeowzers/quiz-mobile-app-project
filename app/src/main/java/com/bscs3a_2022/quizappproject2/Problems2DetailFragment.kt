package com.bscs3a_2022.quizappproject2

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
import com.bscs3a_2022.quizappproject2.databinding.FragmentProblems2DetailBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class Problems2DetailFragment : Fragment() {
    private var _binding: FragmentProblems2DetailBinding? = null
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
        _binding = FragmentProblems2DetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Edit Problem"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val sharedViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = Problems2DetailViewModelFactory(dataSource, application, sharedViewModel.id, sharedViewModel.problemId)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[Problems2DetailViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.curProblem.observe(viewLifecycleOwner) {
            it?.let {
                sharedViewModel.problems2 = it
                when(it.answer) {
                    1 -> binding.radiobutton1.isChecked = true
                    2 -> binding.radiobutton2.isChecked = true
                    3 -> binding.radiobutton3.isChecked = true
                    4 -> binding.radiobutton4.isChecked = true
//                    else -> binding.radioGroup.clearCheck()
                }
            }
        }

        binding.save.setOnClickListener {
            val description = binding.problem2Description.text.toString()
            val choice1 = binding.editTextChoice1.text.toString()
            val choice2 = binding.editTextChoice2.text.toString()
            val choice3 = binding.editTextChoice3.text.toString()
            val choice4 = binding.editTextChoice4.text.toString()
            val answer: Int = when{
                binding.radiobutton1.isChecked -> 1
                binding.radiobutton2.isChecked -> 2
                binding.radiobutton3.isChecked -> 3
                binding.radiobutton4.isChecked -> 4
                else -> 0
            }
            viewModel.updateProblem2(description,choice1,choice2,choice3,choice4, answer)
            this.findNavController().navigate(
                Problems2DetailFragmentDirections.actionProblems2DetailFragmentToProblemsLFragment()
            )
        }
        binding.deleteproblem2.setOnClickListener{
            Toast.makeText(context, "Problem Deleted...", Toast.LENGTH_SHORT).show()
            viewModel.deleteProblem2()
            this.findNavController().navigate(
                Problems2DetailFragmentDirections.actionProblems2DetailFragmentToProblemsLFragment()
            )
        }
        binding.radiobutton1.setOnClickListener{
            binding.radiobutton2.isChecked = false
            binding.radiobutton3.isChecked = false
            binding.radiobutton4.isChecked = false
            viewModel.rightChoice = 1
        }
        binding.radiobutton2.setOnClickListener{
            binding.radiobutton1.isChecked = false
            binding.radiobutton3.isChecked = false
            binding.radiobutton4.isChecked = false
            viewModel.rightChoice = 2
        }
        binding.radiobutton3.setOnClickListener{
            binding.radiobutton2.isChecked = false
            binding.radiobutton1.isChecked = false
            binding.radiobutton4.isChecked = false
            viewModel.rightChoice = 3
        }
        binding.radiobutton4.setOnClickListener{
            binding.radiobutton2.isChecked = false
            binding.radiobutton3.isChecked = false
            binding.radiobutton1.isChecked = false
            viewModel.rightChoice = 4
        }
        return binding.root
    }
}