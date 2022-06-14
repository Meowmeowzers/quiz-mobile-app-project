package com.bscs3a_2022.quizappproject2.quiz.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quiz_set_table")
data class QuizSet (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val quizSetId: Long = 0L,

    @ColumnInfo(name= "quiz_name")
    var quizName: String,

    @ColumnInfo(name= "quiz_description")
    var description: String,

    @ColumnInfo(name= "quiz_subject")
    var subject: String

)