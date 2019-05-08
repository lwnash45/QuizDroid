package edu.washington.lwnash45.quizdroid

import android.app.Application
import android.util.EventLogTags
import android.util.Log

class QuizApp: Application() {

    companion object {
        var topicRepository = QuizData()
    }


    override fun onCreate() {
        super.onCreate()
        Log.i("QUIZ_APP", "Quiz App data loaded")
    }

    class Quiz(val question: String, val options: Array<String>, val correct: Int) {}

    class Topic(val title: String, val description: String, val questions: ArrayList<Quiz>) {}

    interface TopicRepository {
        fun getTopicNames(): ArrayList<String>
        fun getQuiz(title: String): ArrayList<Quiz>
        fun getTopicDescription(topic: String): String
    }
}