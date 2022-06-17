package com.bscs3a_2022.quizappproject2.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.ProblemsFromQuizSet
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
    ///////////////////////////////////////////////

    @Insert
    fun insertProblem(problem: Problems)

    @Update
    fun updateProblem(problem: Problems)

    @Query("SELECT * FROM quiz_set_problems_table ORDER BY problem_id ASC")
    fun getAllProblems(): LiveData<List<Problems>>

    @Query("DELETE FROM quiz_set_problems_table " +
            "WHERE from_quiz_set = :id"
    )
    fun clearProblemsOfQuiz(id: Long)

    @Query(
        "SELECT * FROM quiz_set_table " +
        "JOIN quiz_set_problems_table ON quiz_set_id = problem_id"
    )
    fun getQuizSetAndItsProblems(): Map<QuizSet, List<Problems>>

    @Transaction
    @Query("SELECT * FROM quiz_set_table")
    fun getProblemsFromQuizSet(): List<ProblemsFromQuizSet>

    @Query(
        "SELECT * FROM quiz_set_problems_table " +
        "JOIN quiz_set_problem_choices_table ON problem_id = choice_id"
    )
    fun getProblemAndItsChoices(): Map<Problems, List<Choices>>

}