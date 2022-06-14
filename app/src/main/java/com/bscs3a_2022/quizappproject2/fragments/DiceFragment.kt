package com.bscs3a_2022.quizappproject2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bscs3a_2022.quizappproject2.R
import com.bscs3a_2022.quizappproject2.databinding.FragmentDiceBinding


class DiceFragment : Fragment() {
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    /*private var imgDice : ImageView = binding.imageDice
    private var textModify: TextView = binding.textmodify*/

    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Dice"

        val rollButton = binding.rollbutton
        val addButton = binding.add1button
        rollButton.setOnClickListener{ rollDice()}
        addButton.setOnClickListener { add1() }
    }

    private fun rollDice(){
        Toast.makeText(requireActivity(), "Dice Rolled",
            Toast.LENGTH_SHORT).show()

        val randomInt = (1 .. 6).random()

        binding.textmodify.text = randomInt.toString()
        val drawResource = when (randomInt){
            1 -> R.drawable.svgrepo_com_dice_1
            2 -> R.drawable.svgrepo_com_dice_2
            3 -> R.drawable.svgrepo_com_dice_3
            4 -> R.drawable.svgrepo_com_dice_4
            5 -> R.drawable.svgrepo_com_dice_5
            else -> R.drawable.svgrepo_com_dice_6
        }
        binding.imageDice.setImageResource(drawResource)

    }

    private fun add1(){
        if(binding.textmodify.text == "Hello World Muning!")
            binding.textmodify.text = "0"

        var addValue = binding.textmodify.text.toString().toInt()
        addValue++
        binding.textmodify.text = addValue.toString()

        val drawResource = when (addValue){
            1 -> R.drawable.svgrepo_com_dice_1
            2 -> R.drawable.svgrepo_com_dice_2
            3 -> R.drawable.svgrepo_com_dice_3
            4 -> R.drawable.svgrepo_com_dice_4
            5 -> R.drawable.svgrepo_com_dice_5
            else -> R.drawable.svgrepo_com_dice_6
        }

        binding.imageDice.setImageResource(drawResource)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}