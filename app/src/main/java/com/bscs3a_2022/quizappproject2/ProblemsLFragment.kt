package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bscs3a_2022.quizappproject2.databinding.FragmentProblemslBinding
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabase
import com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory.ShareViewModel

class ProblemsLFragment: Fragment() {
    private var _binding: FragmentProblemslBinding? = null
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
        _binding = FragmentProblemslBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Modify Quiz Problems"

        val shareViewModel: ShareViewModel by activityViewModels()
        val application = requireNotNull(this.activity).application
        val dataSource = QuizDatabase.getInstance(application).quizSetDatabaseDao
        val viewModelFactory = ProblemsLViewModelFactory(dataSource, application, shareViewModel.id)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[ProblemsLViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = ProblemsLAdapter(ProblemItemListener2 { id: Long ->
            shareViewModel.setNewProblemId(id)
            viewModel.onProblems2DetailClicked(id)
        })

        binding.quizProblems2Recycler.adapter = adapter

        viewModel.problem.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.navigateToProblems2Detail.observe(viewLifecycleOwner) { quiz ->
            quiz?.let {
                this.findNavController().navigate(
                    ProblemsLFragmentDirections.actionProblemsLFragmentToProblems2DetailFragment()
                )
                viewModel.onProblems2DetailNavigated()
                Toast.makeText(context, shareViewModel.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        binding.deletequiz.setOnClickListener{
            viewModel.deleteQuiz()
            this.findNavController().navigate(
                ProblemsLFragmentDirections.actionProblemsLFragmentToQuizListFragment()
            )
        }
        binding.addproblem2.setOnClickListener {
            this.findNavController().navigate(
                ProblemsLFragmentDirections.actionProblemsLFragmentToProblems2Fragment()
            )
        }
        fun moveback(){
            this.findNavController().navigate(
                ProblemsLFragmentDirections.actionProblemsLFragmentToQuizListFragment()
            )
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
                moveback()
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