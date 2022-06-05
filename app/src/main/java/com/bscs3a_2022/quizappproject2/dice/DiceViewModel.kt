package com.bscs3a_2022.quizappproject2.dice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bscs3a_2022.quizappproject2.R
import timber.log.Timber


class DiceViewModel : ViewModel() {

    private val _randomInt = MutableLiveData<Int>()
    val randomInt: LiveData<Int> get() = _randomInt

    private val _drawResource = MutableLiveData<Int>()
    val drawResource: LiveData<Int> get() = _drawResource

    val diceText = "Hello World Muning!"


    private var drawResources = listOf(
        R.drawable.svgrepo_com_dice_1,
        R.drawable.svgrepo_com_dice_2,
        R.drawable.svgrepo_com_dice_3,
        R.drawable.svgrepo_com_dice_4,
        R.drawable.svgrepo_com_dice_5,
        R.drawable.svgrepo_com_dice_6
    )

    init {
        Timber.i("DiceViewModel created!")
        _randomInt.value = 0
        _drawResource.value = R.drawable.svgrepo_com_dice
    }

    fun rollDice(){
        _randomInt.value = (1 .. 6).random()
        setImage()
        //setDiceText(randomInt.value)
    }

    fun add1(){
//        randomInt++
        _randomInt.value = (randomInt.value)?.plus(1)
        setImage()
        //setDiceText(randomInt.value)
    }
    /*private fun setDiceText(){
            _diceText.value = x.toString()
    }*/
    private fun setImage(){
        _drawResource.value = when (randomInt.value){
            1 -> drawResources[0]
            2 -> drawResources[1]
            3 -> drawResources[2]
            4 -> drawResources[3]
            5 -> drawResources[4]
            else -> drawResources[5]
        }

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("DiceViewModel destroyed!" )
    }
}