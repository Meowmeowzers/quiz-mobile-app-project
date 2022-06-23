package com.bscs3a_2022.quizappproject2.quiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Choices
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet

@Database(entities = [QuizSet::class, Problems::class, Choices::class, Problems2::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract val quizSetDatabaseDao: QuizDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuizDatabase::class.java,
                        "quiz_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}