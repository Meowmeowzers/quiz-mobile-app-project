package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_set_problems_table")
data class Problems (

    @PrimaryKey(autoGenerate = true)
    val quizSetId: Long = 0L,

    @ColumnInfo(name = "from_quiz_set")
    var fromQuiz: Long = 0L,

    @ColumnInfo(name = "problem_description")
    var description: String = ""



//    @ColumnInfo(name = "quality_rating")
//    var sleepQuality: Int = -1

)