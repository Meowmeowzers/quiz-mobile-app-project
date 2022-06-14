package com.bscs3a_2022.quizappproject2.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuizDatabaseDao {

    @Insert
    fun insert(quiz: QuizSet)

    @Update
    fun update(quiz: QuizSet)

    @Query("SELECT * from quiz_set_table WHERE quizSetId = :key")
    fun get(key: Long): QuizSet?

    @Query("DELETE FROM quiz_set_table")
    fun clear()

    @Query("SELECT * FROM quiz_set_table ORDER BY quizSetId DESC LIMIT 1")
    fun getRecentQuiz(): QuizSet?

    @Query("SELECT * FROM quiz_set_table ORDER BY quizSetId DESC")
    fun getAllQuizSets(): LiveData<List<QuizSet>>

    @Query("SELECT * FROM quiz_set_table")
    fun getAll(): LiveData<List<QuizSet>>

}