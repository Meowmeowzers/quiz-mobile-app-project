package com.bscs3a_2022.quizappproject2.quiz

import android.widget.TextView
import androidx.databinding.BindingAdapter
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

//    private fun quizTitleFormat(quizName: String): String {
//        return quizName
//    }
//    private fun quizSubjectFormat(quizSubject: String): String {
//        return quizSubject
//    }
//    private fun quizDescriptionFormat(quizDescription: String): String {
//        return quizDescription
//    }


