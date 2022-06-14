package com.bscs3a_2022.quizappproject2.quiz.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_set_answer_items_table")
data class QuizSetAnswerItems (

    @PrimaryKey(autoGenerate = true)
    val quizItemId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set_problem")
    var fromQuizSetProblem: Long = 0L,

    @ColumnInfo(name = "quiz_item_description")
    var quizItemDescription: String = "",

    @ColumnInfo(name = "quiz_item_answer")
    var quizItemAnswer: Int = 0



//    @ColumnInfo(name = "quality_rating")
//    var sleepQuality: Int = -1

)