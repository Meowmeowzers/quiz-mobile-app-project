package com.bscs3a_2022.quizappproject2

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2

class TakeListViewModel(
    val database: QuizDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

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

class TakeListViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TakeListViewModel::class.java)) {
            return TakeListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
