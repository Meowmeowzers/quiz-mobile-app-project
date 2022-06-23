package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ProblemChoicesEditViewModel(
    val database: QuizDatabaseDao,
    application: Application,
    id: Long
) : AndroidViewModel(application){

    private var _listofChoices: LiveData<List<Choices>>
    var listofChoices: LiveData<List<Choices>>
    var selectedId: Long = 0L
    var problem: LiveData<Problems>
    init{
        _listofChoices = database.getChoicesOfProblem(id)
        listofChoices = _listofChoices
        selectedId = id
        problem = database.getProblem(id)
    }

    fun createChoice(quiz: Long, problem: Long, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newChoice = Choices(0, quiz, problem, description)
            insert(newChoice)
        }
    }

    private fun insert(choices: Choices) {
        Timber.i("db process")
        database.insertChoice(choices)
    }

    fun clearAChoice(id: Long) {
        Timber.i("db process")
        viewModelScope.launch(Dispatchers.IO) {
            database.clearAChoice(id)
        }
    }

    private val _navigateToChoiceDetails = MutableLiveData<Long>()
    val navigateToChoiceDetails get() = _navigateToChoiceDetails

    fun onChoiceClicked(id:Long){
        _navigateToChoiceDetails.value = id
    }
    fun onChoiceDetailsNavigated() {
        _navigateToChoiceDetails.value = null
    }

//    @Bindable
//    fun DescriptionListener(id: Long): String{
//
//        return
//    }
}

class ProblemChoicesEditViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProblemChoicesEditViewModel::class.java)) {
            return ProblemChoicesEditViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}