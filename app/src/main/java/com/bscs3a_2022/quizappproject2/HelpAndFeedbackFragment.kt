package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bscs3a_2022.quizappproject2.databinding.FragmentHelpAndFeedbackBinding

class HelpAndFeedbackFragment : Fragment() {

    private var _binding: FragmentHelpAndFeedbackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHelpAndFeedbackBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Help and Feedback"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}