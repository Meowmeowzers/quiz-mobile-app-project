package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.accounts.AuthenticatorDescription
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizProblemsListViewModel (
    val database: QuizDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    val listofProblems = database.getAllProblems()
    //val quizProblemList = database.getQuizSetAndItsProblems()
//    val quizProblemListz get() =  MutableLiveData(quizProblemList.toList())

    fun getAllProblems(){
        viewModelScope.launch(Dispatchers.IO) {
            database.getAllProblems()
        }
    }
    fun createProblem(fromQuiz: Long, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newProblems = Problems(0,fromQuiz,description)
            insert(newProblems)
        }
    }
    private fun insert(problems: Problems) {
        Timber.i("db process")
        database.insertProblem(problems)
    }

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
    private val _navigateToProblemChoicesDetails = MutableLiveData<Long>()
    val navigateToProblemChoicesDetails get() = _navigateToProblemChoicesDetails

    fun onProblemItemClicked(id:Long){
        _navigateToProblemChoicesDetails.value = id
    }
    fun onProblemItemNavigated() {
        _navigateToProblemChoicesDetails.value = null
    }
}