package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import timber.log.Timber

class QuizTakeActiveViewModel(
    val database: QuizDatabaseDao,
    application: Application,
    id: Long
) : AndroidViewModel(application){

    private var _listOfProblems: LiveData<List<Problems>>
    var listOfProblems: LiveData<List<Problems>> get() = _listOfProblems
    private var _listOfChoices: LiveData<List<Choices>>
    var listOfChoices: LiveData<List<Choices>> get() = _listOfChoices

    init{
        _listOfProblems = database.getProblemsOfQuiz(id)
        listOfProblems = _listOfProblems
        _listOfChoices = database.getChoicesOfProblem(id)
        listOfChoices = _listOfChoices
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
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizTakeActiveViewModel::class.java)) {
            return QuizTakeActiveViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}