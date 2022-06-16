package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_set_problems_table")
data class Problems (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "problem_id")
    val problemId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set")
    var fromQuiz: Long,

    @ColumnInfo(name = "problem_description")
    var description: String

)

//data class ProblemsOfQuizSet(
//
//)