package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

open class QuizCreateViewModel (
    val database: QuizDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    private var quiz = MutableLiveData<QuizSet?>()
    val quizList = database.getAllQuizSetAsc()

    fun createQuiz(quizName: String, quizDescription: String, subject: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newQuiz = QuizSet(0,quizName,quizDescription , subject)
            insert(newQuiz)
        }
    }
    fun clearDb() {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {
            clear()
        }
    }

    private fun getTonightFromDatabase(): QuizSet? {
        return database.getRecentQuizSet()
    }

    private fun insert(quizSet: QuizSet) {
        Timber.i("db process")
        database.insertQuizSet(quizSet)
    }
    private fun update(quizSet: QuizSet) {
        database.updateQuizSet(quizSet)
    }
    private fun clear() {
        Timber.i("db process")
        database.clearQuizSetDataBase()
    }
}