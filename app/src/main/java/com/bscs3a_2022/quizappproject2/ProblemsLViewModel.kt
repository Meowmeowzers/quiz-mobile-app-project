package com.bscs3a_2022.quizappproject2

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProblemsLViewModel(
    val database: QuizDatabaseDao,
    application: Application,
    id: Long
) : AndroidViewModel(application) {

    private var _problem: LiveData<List<Problems2>>
    var problem: LiveData<List<Problems2>>
    private var _quizId = id
    init {
        _problem = database.getProblems2(id)
        problem = _problem
    }

    fun deleteQuiz(){
        viewModelScope.launch(Dispatchers.IO) {
            database.deleteProblem2(_quizId)
            database.clearAQuizSet(_quizId)
        }
    }

    private val _navigateToProblems2Detail = MutableLiveData<Long>()
    val navigateToProblems2Detail get() = _navigateToProblems2Detail

    fun onProblems2DetailClicked(id: Long) {
        _navigateToProblems2Detail.value = id
    }

    fun onProblems2DetailNavigated() {
        _navigateToProblems2Detail.value = null
    }
}

class ProblemsLViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProblemsLViewModel::class.java)) {
            return ProblemsLViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
