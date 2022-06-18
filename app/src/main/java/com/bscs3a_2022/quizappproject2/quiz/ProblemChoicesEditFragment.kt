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
import com.bscs3a_2022.quizappproject2.databinding.QuizProblemChoicesEditBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.*

class ProblemChoicesEditFragment : Fragment() {
    private var _binding: QuizProblemChoicesEditBinding? = null
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
        _binding = QuizProblemChoicesEditBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add/Edit Choice"

        val sharedViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = ProblemChoicesEditViewModelFactory(dataSource, application, sharedViewModel.id)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[ProblemChoicesEditViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewmodel = viewModel
        viewModel.problem.observe(viewLifecycleOwner){
            it?.let{
                sharedViewModel.problems = it
                binding.problem = sharedViewModel.problems
            }
        }
        val adapter = ProblemChoicesEditAdapter(ChoiceItemListener { choice: Long ->
//            sharedViewModel.setNewChoiceId(choice)
//            viewModel.onChoiceClicked(choice)
            viewModel.clearAChoice(choice)
        })
        binding.problemChoicesRecyclerView.adapter = adapter
//        binding.problem = sharedViewModel.problemId
        viewModel.selectedId = sharedViewModel.problemId


        viewModel.listofChoices.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

//        viewModel.navigateToProblemChoicesDetails.observe(viewLifecycleOwner) { problem ->
//            problem?.let {
//                this.findNavController().navigate(
//                    QuizProblemsListFragmentDirections.actionQuizProblemsListFragmentToQuizProblemChoicesEditFragment(problem))
//                viewModel.onProblemItemNavigated()
//            }
//        }
        binding.addproblemchoice.setOnClickListener {
            Toast.makeText(context, sharedViewModel.id.toString(), Toast.LENGTH_SHORT).show()
            val choiceDescription = binding.editTextCreateChoiceDescription.text.toString()
            viewModel.createChoice(sharedViewModel.id, choiceDescription)

        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}