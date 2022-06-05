package com.bscs3a_2022.quizappproject2.dice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.databinding.FragmentDiceBinding


class DiceFragment : Fragment() {
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DiceViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Dice"

        /** Setting up LiveData observation relationship **/
        viewModel.randomInt.observe(viewLifecycleOwner, Observer { newInt ->
            if(viewModel.randomInt.value == 0){
                binding.textmodify.text = viewModel.diceText
            }
            else
                binding.textmodify.text = newInt.toString()
        })
        viewModel.drawResource.observe(viewLifecycleOwner, Observer { newResource ->
            binding.imageDice.setImageResource(newResource)
        })
//        viewModel.diceText.observe(viewLifecycleOwner, Observer { newText ->
//            binding.textmodify.text = newText
//        })

        binding.rollbutton.setOnClickListener{ rollDice()}
        binding.add1button.setOnClickListener { add1() }

//        binding.imageDice.setImageResource(viewModel.drawResource)
//        binding.textmodify.text = viewModel.diceText
        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/

    private fun rollDice(){
        Toast.makeText(requireActivity(), "Dice Rolled",
            Toast.LENGTH_SHORT).show()

//        val randomInt = (1 .. 6).random()
        viewModel.rollDice()
//        binding.textmodify.text = viewModel.randomInt.toString()
//        binding.imageDice.setImageResource(viewModel.drawResource)
    }

    private fun add1(){
//        var addValue = binding.textmodify.text.toString().toInt()
//        addValue++
        viewModel.add1()
//        binding.textmodify.text = viewModel.randomInt.toString()
//        binding.imageDice.setImageResource(viewModel.drawResource)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}