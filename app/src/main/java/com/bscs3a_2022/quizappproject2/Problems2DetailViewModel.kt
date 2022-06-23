package com.bscs3a_2022.quizappproject2

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Problems2DetailViewModel (
    val database: QuizDatabaseDao,
    application: Application,
    id: Long,
    id2: Long
    ) : AndroidViewModel(application) {
    private var selectedId: Long = 0L
    private var selectedId2: Long = 0L
    var rightChoice: Int?
    var curProblem: LiveData<Problems2>

    init {
        selectedId = id
        selectedId2 = id2
        curProblem = database.getAProblem2(id2)
        rightChoice = curProblem.value?.answer
    }

    fun updateProblem2(description: String, choice1: String, choice2: String, choice3: String, choice4: String, answer: Int) {
        val x = Problems2(selectedId2, selectedId, description, choice1, choice2, choice3, choice4, answer)
        viewModelScope.launch(Dispatchers.IO) {
            database.updateProblems2(x)
        }
    }
    fun deleteProblem2(){
        viewModelScope.launch(Dispatchers.IO) {
            database.deleteAProblem2(selectedId2)
        }
    }
}

class Problems2DetailViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long,
    private val id2: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Problems2DetailViewModel::class.java)) {
            return Problems2DetailViewModel(dataSource, application, id, id2 ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}