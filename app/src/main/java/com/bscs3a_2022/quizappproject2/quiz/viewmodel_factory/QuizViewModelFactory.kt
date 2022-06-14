package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao

class QuizViewModelFactory (
    private val dataSource: QuizDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}