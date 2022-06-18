package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.quiz.QuizTakeResult
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

class QuizTakeResultViewModel(
    val database: QuizDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    private var quizSet = MutableLiveData<QuizSet?>()
    val quizList = database.getAllQuizSetAsc()

    //////////////////////////////////////////////////
    private val _navigateToQuizTake = MutableLiveData<Long>()
    val navigateToQuizTake get() = _navigateToQuizTake

    fun onQuizItemClicked(id:Long){
        _navigateToQuizTake.value = id
    }
    fun onQuizItemDetailNavigated() {
        _navigateToQuizTake.value = null
    }
}

class QuizTakeResultViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizTakeResultViewModel::class.java)) {
            return QuizTakeResultViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}