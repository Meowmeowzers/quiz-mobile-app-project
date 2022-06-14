package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizListViewModel (
    val database: QuizDatabaseDao,
    application: Application) : AndroidViewModel(application){

    private var quizSet = MutableLiveData<QuizSet?>()
    val quizList = database.getAll()

    fun clearDb() {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {
            clear()
        }
    }
    private fun getTonightFromDatabase(): QuizSet? {
        return database.getRecentQuiz()
    }

    private fun insert(quizSet: QuizSet) {
        Timber.i("db process")
        database.insert(quizSet)
    }
    private fun update(quizSet: QuizSet) {
        database.update(quizSet)
    }
    private fun clear() {
        Timber.i("db process")
        database.clear()
    }
}