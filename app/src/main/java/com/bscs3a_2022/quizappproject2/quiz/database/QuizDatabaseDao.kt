package com.bscs3a_2022.quizappproject2.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

@Dao
interface QuizDatabaseDao {

    @Insert
    fun insertQuizSet(quiz: QuizSet)

    @Update
    fun updateQuizSet(quiz: QuizSet)

    @Query("SELECT * from quiz_set_table WHERE quiz_set_id = :key")
    fun getQuizSet(key: Long): QuizSet?

    @Query("DELETE FROM quiz_set_table")
    fun clearQuizSetDataBase()

    @Query("SELECT * FROM quiz_set_table ORDER BY quiz_set_id ASC LIMIT 1")
    fun getRecentQuizSet(): QuizSet?

    @Query("SELECT * FROM quiz_set_table ORDER BY quiz_set_id ASC")
    fun getAllQuizSetAsc(): LiveData<List<QuizSet>>

    @Query("SELECT * FROM quiz_set_table ORDER BY quiz_set_id DESC")
    fun getAllQuizSetDesc(): LiveData<List<QuizSet>>

}