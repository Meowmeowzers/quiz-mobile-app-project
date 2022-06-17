package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.QuizListAdapter
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizListViewModel (
    val database: QuizDatabaseDao,
    application: Application) : AndroidViewModel(application){

    private var quizSet = MutableLiveData<QuizSet?>()
    val quizList = database.getAllQuizSetAsc()

    var quizId = 0L
    fun clearDb() {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {
            clear()
        }
    }
//    private fun getRecentQuizFromDatabase(): QuizSet? {
//        return database.getRecentQuizSet()
//    }
//    private fun insert(quizSet: QuizSet) {
//        database.insertQuizSet(quizSet)
//    }
//    private fun update(quizSet: QuizSet) {
//        database.updateQuizSet(quizSet)
//    }
    private fun clear() {
        database.clearQuizSetDataBase()
    }
    //////////////////////////////////////////////////
    private val _navigateToQuizDetails = MutableLiveData<Long>()
    val navigateToQuizDetails get() = _navigateToQuizDetails

    fun onQuizItemClicked(id:Long){
        _navigateToQuizDetails.value = id
    }
    fun onQuizItemDetailNavigated() {
        _navigateToQuizDetails.value = null
    }
}