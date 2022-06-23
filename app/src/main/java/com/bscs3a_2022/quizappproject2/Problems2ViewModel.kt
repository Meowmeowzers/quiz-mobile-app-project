package com.bscs3a_2022.quizappproject2

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Problems2ViewModel (
        val database: QuizDatabaseDao,
        application: Application,
        id: Long
    ) : AndroidViewModel(application) {
//    private var _problem: LiveData<List<Problems2>>
//    var problem: LiveData<List<Problems2>>
    var rightChoice: Int = 0
    var selectedId: Long = 0L

    init {
//        _problem = database.getProblems2(id)
//        problem = _problem
        selectedId = id

    }

    fun createProblem2(description: String, choice1: String, choice2: String, choice3: String, choice4: String) {
        val newProblem2 = Problems2(0, selectedId,description,choice1,choice2,choice3,choice4, rightChoice)
        viewModelScope.launch(Dispatchers.IO) {
            database.insertProblems2(newProblem2)
        }
    }
}

class Problems2ViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Problems2ViewModel::class.java)) {
            return Problems2ViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
