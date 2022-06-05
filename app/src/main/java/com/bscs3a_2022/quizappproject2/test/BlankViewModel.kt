package com.bscs3a_2022.quizappproject2.test

import androidx.lifecycle.ViewModel
import com.bscs3a_2022.quizappproject2.R
import timber.log.Timber

class BlankViewModel : ViewModel() {
    private var text: String = "Android"
    private var image: Int = R.drawable.ic_baseline_account_circle_24

    init {
        Timber.i("ViewModel created!")
    }
    fun getText(): String {
        return text
    }
    fun getImage(): Int {
        return image
    }
    fun setNew(){
        text = "Happy Coding...!"
        image = R.drawable.ic_baseline_settings_24
    }


}