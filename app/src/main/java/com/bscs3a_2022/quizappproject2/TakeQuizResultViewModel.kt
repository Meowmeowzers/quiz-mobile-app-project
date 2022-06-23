package com.bscs3a_2022.quizappproject2

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao

class TakeQuizResultViewModel(
    val database: QuizDatabaseDao,
    application: Application,
    val score: Int,
    val over: Int
) : AndroidViewModel(application) {

    private val _navigateToQuizTake = MutableLiveData<Long>()
    val navigateToQuizTake get() = _navigateToQuizTake

    fun onQuizItemClicked(id:Long){
        _navigateToQuizTake.value = id
    }
    fun onQuizItemDetailNavigated() {
        _navigateToQuizTake.value = null
    }
}

class TakeQuizResultViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val score: Int,
    private val over: Int
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TakeQuizResultViewModel::class.java)) {
            return TakeQuizResultViewModel(dataSource, application, score, over) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
