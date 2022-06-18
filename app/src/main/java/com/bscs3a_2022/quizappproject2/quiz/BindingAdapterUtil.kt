package com.bscs3a_2022.quizappproject2.quiz

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

    @BindingAdapter("quizTitle")
    fun TextView.setQuizTitleString(item: QuizSet){
       text = item.name
    }
    @BindingAdapter("quizSubject")
    fun TextView.setQuizSubjectString(item: QuizSet){
        text = item.subject
    }
    @BindingAdapter("quizDescription")
    fun TextView.setQuizDescriptionString(item: QuizSet){
        text = item.description
    }
/*---------------------------------------------------------------*/
    @BindingAdapter("problemId")
    fun TextView.setProblemId(item: Problems){
        text = item.problemId.toString()
    }
    @BindingAdapter("problemFromQuiz")
    fun TextView.setProblemFromQuiz(item: Problems){
        text = item.fromQuiz.toString()
    }
    @BindingAdapter("problemDescription")
    fun TextView.setProblemDescription(item: Problems){
        text = item.description
    }
/*---------------------------------------------------------------*/
    @BindingAdapter("choiceId")
    fun TextView.setChoiceId(item: Choices){
        text = item.choiceId.toString()
    }
    @BindingAdapter("choiceFromProblem")
    fun TextView.setChoiceFromProblem(item: Choices){
        text = item.fromProblem.toString()
    }
    @BindingAdapter("choiceDescription")
    fun TextView.setChoiceDescription(item: Choices){
        text = item.description
    }
//    @InverseBindingAdapter(attribute = "choiceDescription")
//    fun TextView.getChoiceDescription(item : Choices) : String {
//        return item.description
//}

