package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_set_problem_choices_table")
data class Choices (

    @PrimaryKey(autoGenerate = true)
    val quizItemId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set_problem")
    var fromProblem: Long ,

    @ColumnInfo(name = "choice_description")
    var description: String,

    @ColumnInfo(name = "choice_answer")
    var answer: Int



//    @ColumnInfo(name = "quality_rating")
//    var sleepQuality: Int = -1

)