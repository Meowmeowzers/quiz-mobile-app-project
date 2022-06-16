package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizProblemsListViewModel (
    val database: QuizDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    val quizProblemList = database.getQuizSetAndItsProblems()

    fun clearProblems() {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
//    private fun getTonightFromDatabase(): QuizSet? {
//        return database.getRecentQuizSet()
//    }
//    private fun insert(quizSet: QuizSet) {
//        Timber.i("db process")
//        database.insertQuizSet(quizSet)
//    }
//    private fun update(quizSet: QuizSet) {
//        database.updateQuizSet(quizSet)
//    }
//    private fun clear() {
//        Timber.i("db process")
//        database.clearQuizSetDataBase()
//    }
}