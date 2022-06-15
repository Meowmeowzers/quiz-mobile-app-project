package com.bscs3a_2022.quizappproject2.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BlankViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BlankViewModel::class.java]

        binding.myImage.setImageResource(viewModel.getImage())
        binding.myText.text = viewModel.getText()

        binding.myButton.setOnClickListener {
            viewModel.setNew()
            binding.myImage.setImageResource(viewModel.getImage())
            binding.myText.text = viewModel.getText()
        }
        return binding.root
    }

}