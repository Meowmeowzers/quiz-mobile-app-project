package com.bscs3a_2022.quizappproject2.quiz

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.R
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeBinding
import com.bscs3a_2022.quizappproject2.databinding.QuizTakeProblemCardBinding
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizTakeAdapter
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizTakeChoiceListener
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizTakeChoicesAdapter
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.*

class QuizTakeActive : Fragment() {
    private var _binding: QuizTakeBinding? = null
    private val binding get() = _binding!!
    private var _binding2: QuizTakeProblemCardBinding? = null
    private val binding2 get() = _binding2!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizTakeBinding.inflate(inflater, container, false)
        _binding2 = QuizTakeProblemCardBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz"

        val shareViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = QuizTakeActiveViewModelFactory(dataSource, application, shareViewModel.id ,shareViewModel.problemId)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuizTakeActiveViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter2 = QuizTakeChoicesAdapter(
            QuizTakeChoiceListener { choiceId: Long ->
                viewModel.onChoiceClicked(choiceId)
                shareViewModel.choiceId = choiceId
            }
        )
        val adapter = QuizTakeAdapter(adapter2)
        binding.quizTakeProblemItemsRecycler.adapter = adapter

        viewModel.listOfProblems.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.listOfChoices.observe(viewLifecycleOwner) {
            it?.let {

                adapter2.submitList(it)
            }
        }
//        viewModel.navigateToQuizDetails.observe(viewLifecycleOwner) { quiz ->
//            quiz?.let {
//                this.findNavController().navigate(
//                    QuizListFragmentDirections.actionQuizListFragmentToQuizProblemsListFragment())
//                viewModel.onQuizItemDetailNavigated()
//                Toast.makeText(context, shareViewModel.id.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//        binding.addquizitem.setOnClickListener{
//            findNavController().navigate(R.id.action_quizListFragment_to_quizCreateFragment)
//        }

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