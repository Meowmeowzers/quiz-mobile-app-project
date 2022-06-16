package com.bscs3a_2022.quizappproject2.quiz.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quiz_set_table")
data class QuizSet (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quiz_set_id")
    val quizSetId: Long = 0L,

    @ColumnInfo(name= "quiz_set_name")
    var name: String,

    @ColumnInfo(name= "quiz_set_description")
    var description: String,

    @ColumnInfo(name= "quiz_set_subject")
    var subject: String

)