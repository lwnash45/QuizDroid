package edu.washington.lwnash45.quizdroid

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONArray
import android.content.Context
import org.json.JSONObject
import java.lang.Exception
import java.nio.charset.Charset


class QuizApp: Application() {

    companion object {
        lateinit var shared: QuizApp
        lateinit var topicRepository: TopicRepository
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QUIZ_APP", "Quiz App data loaded")
        shared = this
        topicRepository = QuizData(applicationContext)
    }

    class Quiz(val question: String, val options: List<String>, val correct: Int) {}

    class Topic(val title: String, val description: String, val questions: List<Quiz>) {}

    interface TopicRepository {
        fun getTopicNames(): ArrayList<String>
        fun getQuiz(title: String): List<Quiz>?
        fun getTopicDescription(topic: String): String
    }


    class QuizData: QuizApp.TopicRepository {

        private var topics: List<Topic>

        constructor(context: Context) {
            val jsonArray = loadJsonData(context)
            topics = getTopics(jsonArray)
        }

        override fun getTopicNames(): ArrayList<String> {
            var output: ArrayList<String> = ArrayList()
            topics.forEach { output.add(it.title) }
            return output
        }

        override fun getQuiz(title: String): List<Quiz>? {
            for (i in 0 until topics.size) {
                if (topics[i].title.equals(title)) {
                    return topics[i].questions
                }
            }
            return null
        }

        override fun getTopicDescription(topic: String): String {
            for (i in 0 until topics.size) {
                if (topics[i].title.equals(topic)) {
                    return topics[i].description
                }
            }
            return ""
        }

        private fun getTopics(arr: JSONArray): ArrayList<Topic> {
            var topics = arrayListOf<Topic>()
            for (i in 0 until arr.length()) {
                topics.add(getTopic(arr.get(i) as JSONObject))
            }
            return topics
        }

        private fun getTopic(obj: JSONObject): Topic {
            var title = obj.get("title") as String
            var description = obj.get("desc") as String
            var questions = getQuizes(obj.get("questions") as JSONArray)
            return Topic(title, description, questions)
        }

        private fun getQuizes(questions: JSONArray): ArrayList<Quiz> {
            var quizes = arrayListOf<Quiz>()
            for (i in 0 until questions.length()) {
                quizes.add(makeQuiz(questions.get(i) as JSONObject))
            }
            return quizes
        }

        private fun makeQuiz(question: JSONObject): Quiz {
            var text = question.get("text") as String
            var answer = question.get("answer") as String
            var answers = makeOptions(question.get("answers") as JSONArray)
            return Quiz(text, answers, answer.toInt() - 1)
        }

        private fun makeOptions(options: JSONArray): ArrayList<String> {
            var output = arrayListOf<String>()
            for (i in 0 until options.length()) {
                output.add(options.get(i) as String)
            }
            return output
        }

        private fun loadJsonData(context: Context): JSONArray {
            val jsonString: String? = try {
                val inputStream = context.assets.open("data/questions.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                String(buffer, Charsets.UTF_8)
            } catch (e: Exception) {
                null
            }
            return JSONArray(jsonString)
        }
    }
}