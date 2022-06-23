package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.FragmentTakeListBinding
import com.bscs3a_2022.quizappproject2.quiz.adapters.QuizSetListener
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class TakeListFragment: Fragment() {
    private var _binding: FragmentTakeListBinding? = null
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
        _binding = FragmentTakeListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Select a Quiz"

        val shareViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = TakeListViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[TakeListViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = TakeListAdapter(QuizSetListener { id: Long ->
            shareViewModel.setNewId(id)
            viewModel.onQuizItemClicked(id)
        })
        binding.takeListRecycler.adapter = adapter

        viewModel.quizList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.navigateToQuizTake.observe(viewLifecycleOwner) { quiz ->
            quiz?.let {
                this.findNavController().navigate(
                    TakeListFragmentDirections.actionTakeListFragmentToTakeQuizFragment())
                viewModel.onQuizItemDetailNavigated()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
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