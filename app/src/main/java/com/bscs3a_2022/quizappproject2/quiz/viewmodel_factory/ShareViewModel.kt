package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

class ShareViewModel: ViewModel(){

    lateinit var quizSet: QuizSet
    lateinit var problems: Problems
    lateinit var choices: Choices

    var id :Long = 0L
    var problemId: Long = 0L
    var choiceId: Long = 0L

    fun setNewId(newId: Long) {
        id = newId
    }
    fun setNewProblemId(newId: Long) {
        id = newId
    }
    fun setNewChoiceId(newId: Long) {
        id = newId
    }

    var description: String = ""


}

//class ShareViewModelFactory(private val dataSource: QuizDatabaseDao) : ViewModelProvider.Factory {
//
//
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ShareViewModel::class.java)) {
//            return ShareViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}