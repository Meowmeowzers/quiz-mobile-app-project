package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.FragmentTakeQuizResultBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class TakeQuizResultFragment : Fragment() {
    private var _binding: FragmentTakeQuizResultBinding? = null
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
        _binding = FragmentTakeQuizResultBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz Results"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val shareViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = TakeQuizResultViewModelFactory(dataSource, application, shareViewModel.score, shareViewModel.over)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[TakeQuizResultViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.goback.setOnClickListener {
            this.findNavController().navigate(
                TakeQuizResultFragmentDirections.actionTakeQuizResultFragmentToTakeListFragment()
            )
            shareViewModel.score = 0
            shareViewModel.over = 0
        }

//        fun moveback(){
//            this.findNavController().navigate(
//                ProblemsLFragmentDirections.actionProblemsLFragmentToQuizListFragment()
//            )
//        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            moveback()
        }
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_quiz_list, menu)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}