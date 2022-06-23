package com.bscs3a_2022.quizappproject2.quiz

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import timber.log.Timber
import kotlin.random.Random

class TakeQuizViewModel (
    val database: QuizDatabaseDao,
    application: Application,
    id: Long
) : AndroidViewModel(application) {
    private var _problem: LiveData<List<Problems2>>
    var problem: LiveData<List<Problems2>> get() = _problem
    private val seed = System.currentTimeMillis()

    var problemCount: Int = 0
    var answeredCount: Int = 0
    private var selectedProblem: Long = 0L
    private var selectedChoice: Int = 0
    private var check = false
    var score: Int = 0

    private val _click = MutableLiveData<Int>()
    val click get() = _click

    private var answeredArray: MutableList<Long?> = mutableListOf()
    private var checkArray: MutableList<Long?> = mutableListOf()

    init {
        _problem = Transformations.map(database.getProblems2(id)){
            it.shuffled(Random(seed))
        }
        problem = _problem
    }

    fun onChoiceClick(x: Problems2, num: Int){

        fun test(){
            if(x.answer == num && score < problemCount){
                if(!checkArray.contains(x.problemId)) {
                        score++
                        checkArray.add(x.problemId)
                }
            }
            else if (x.answer != num && score != 0){
                if(checkArray.contains(x.problemId)) {
                    score--
                    checkArray.remove(x.problemId)
                }
            }
        }

        if(!answeredArray.contains(x.problemId)) {
            answeredArray.add(x.problemId)
            answeredCount++
            test()
        }
        else if(answeredArray.contains(x.problemId)){
            test()
        }

        selectedProblem = x.problemId
        selectedChoice = num

        _click.value = num
        Timber.i(x.answer.toString()+" is answer")
        Timber.i("$score is score")
    }

    fun onClickAfter() {
        _click.value = null
    }

}

class TakeQuizViewModelFactory(
    private val dataSource: QuizDatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TakeQuizViewModel::class.java)) {
            return TakeQuizViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
