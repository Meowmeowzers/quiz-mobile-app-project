package com.bscs3a_2022.quizappproject2.quiz.viewmodel_factory

import android.app.Application
import androidx.lifecycle.*
import com.bscs3a_2022.quizappproject2.quiz.database.QuizDatabaseDao
import com.bscs3a_2022.quizappproject2.quiz.database.entities.Problems2
import com.bscs3a_2022.quizappproject2.quiz.database.entities.QuizSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizListViewModel (
    val database: QuizDatabaseDao,
    application: Application) : AndroidViewModel(application){
    val quizList = database.getAllQuizSetAsc()


//    fun createQuizAtStart() {
//            val newQuiz = QuizSet(
//                1,
//                "Math is Fun",
//                "Example Math quiz",
//                "Mathematics"
//            )
//
//            val newProblem1 = Problems2(
//                0,
//                1,
//                "1 + 1 = ?",
//                "2",
//                "11",
//                "1",
//                "Haha I dont know....",
//                1
//            )
//
//            val newProblem2 = Problems2(
//                0,
//                1,
//                "5 + 5 =",
//                "55",
//                "25",
//                "10",
//                "0",
//                3
//            )
//
//            val newProblem3 = Problems2(
//                0,
//                1,
//                "9 * 9 =",
//                "99",
//                "0",
//                "18",
//                "81",
//                4
//            )
//
//            val newProblem4 = Problems2(
//                0,
//                1,
//                "There were 150 pizzas in total at the pizza shop. A customer bought 7 pizza. How many pizzas are left?",
//                "157",
//                "143",
//                "165",
//                "147",
//                2
//            )
//
//            val newProblem5 = Problems2(
//                0,
//                1,
//                "A movie theatre has 25 rows of seats with 20 seats in each row. How many seats are there in total?",
//                "415",
//                "450",
//                "550",
//                "500",
//                4
//            )
//
//            val newProblem6 = Problems2(
//                0,
//                1,
//                "Lana has 2 bags with 2 marbles in each bag. Markus has 2 bags with 3 marbles in each bag. How many more marbles does Markus have?",
//                "2",
//                "4",
//                "3",
//                "6",
//                1
//            )
//
//            val newProblem7 = Problems2(
//                0,
//                1,
//                "Philip walks a total of 1.5 kilometres to and from school each day. After 4 days, how many kilometres will he have walked?",
//                "3.0",
//                "4.5",
//                "6.0",
//                "6.5",
//                3
//            )
//
//            val newProblem8 = Problems2(
//                0,
//                1,
//                "Leo has 2000 Pesos saved up. He uses his money to buy a video game. The video game costs 800 Pesos. How much money does he have left?",
//                "1000",
//                "1800",
//                "900",
//                "1200",
//                4
//            )
//
//            val newProblem9 = Problems2(
//                0,
//                1,
//                "The room has 7 chairs in a row. There are 8 rows. How many chairs are there in total?",
//                "56",
//                "49",
//                "48",
//                "64",
//                1
//            )
//
//            val newProblem10 = Problems2(
//                0,
//                1,
//                "Angelyn has a cube. Its edge measures 18cm. What is the total surface are of the cube?",
//                "1944",
//                "2166",
//                "1734",
//                "1892",
//                1
//            )
//
//            viewModelScope.launch(Dispatchers.IO) {
//                database.insertQuizSet(newQuiz)
//                database.insertProblems2(newProblem1)
//                database.insertProblems2(newProblem2)
//                database.insertProblems2(newProblem3)
//                database.insertProblems2(newProblem4)
//                database.insertProblems2(newProblem5)
//                database.insertProblems2(newProblem6)
//                database.insertProblems2(newProblem7)
//                database.insertProblems2(newProblem8)
//                database.insertProblems2(newProblem9)
//                database.insertProblems2(newProblem10)
//            }
//            Timber.i("Data Created")
//    }



    //////////////////////////////////////////////////
    private val _navigateToQuizDetails = MutableLiveData<Long>()
    val navigateToQuizDetails get() = _navigateToQuizDetails

    fun onQuizItemClicked(id:Long){
        _navigateToQuizDetails.value = id
    }
    fun onQuizItemDetailNavigated() {
        _navigateToQuizDetails.value = null
    }
}

class QuizListViewModelFactory (
    private val dataSource: QuizDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizListViewModel::class.java)) {
            return QuizListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}