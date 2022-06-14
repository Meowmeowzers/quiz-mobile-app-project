package com.bscs3a_2022.quizappproject2.quiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuizSet::class], version = 1, exportSchema = false)
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
                        "quiz_set_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}