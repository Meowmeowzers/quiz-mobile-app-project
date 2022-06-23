package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import timber.log.Timber
import java.util.*


class QuizTakeActiveViewModel(
    val database: QuizDatabaseDao,
    application: Application,
    quizid: Long,
    problemid: Long
) : AndroidViewModel(application){

    private var _listOfProblems: LiveData<List<Problems>>
    private var _listOfChoices: LiveData<List<Choices>>
    var listOfProblems: LiveData<List<Problems>> get() = _listOfProblems
    var listOfChoices: LiveData<List<Choices>> get() = _listOfChoices
//    var x : Map<Problems, List<Choices>>

    init{
        _listOfProblems = database.getProblemsOfQuiz(quizid)
        listOfProblems = _listOfProblems
        _listOfChoices = database.getChoicesOfQuiz(quizid)
        listOfChoices = _listOfChoices
//        x = database.getProblemAndItsChoices()
    }

    fun onChoiceClicked(id:Long){
//        _navigateToChoiceDetails.value = id
        Timber.i("clicked")
    }
    fun onChoiceDetailsNavigated() {
//        _navigateToChoiceDetails.value = null
    }

    //////////////////////////////////////////////////
}

class QuizTakeActiveViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id1: Long,
    private val id2: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizTakeActiveViewModel::class.java)) {
            return QuizTakeActiveViewModel(dataSource, application, id1, id2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}