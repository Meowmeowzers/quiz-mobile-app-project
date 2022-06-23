package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import androidx.lifecycle.ViewModel
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

class ShareViewModel: ViewModel(){

    lateinit var quizSet: QuizSet
    lateinit var problems: Problems
    lateinit var problems2: Problems2
    lateinit var choices: Choices

    var id :Long = 0L
    var problemId: Long = 0L
    var choiceId: Long = 0L

    var over: Int = 0
    var score: Int = 0

    fun setNewId(newId: Long) {
        id = newId
    }
    fun setNewProblemId(newId: Long) {
        problemId = newId
    }
    fun setNewChoiceId(newId: Long) {
        choiceId = newId
    }

    var description: String = ""

}
