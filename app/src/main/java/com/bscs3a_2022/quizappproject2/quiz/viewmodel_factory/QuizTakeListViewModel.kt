package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizTakeListViewModel(
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

class QuizTakeListViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizTakeListViewModel::class.java)) {
            return QuizTakeListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}