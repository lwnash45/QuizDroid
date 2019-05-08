package edu.washington.lwnash45.quizdroid

class QuizData: QuizApp.TopicRepository {


    override fun getTopicNames(): ArrayList<String> {
        var output: ArrayList<String> = ArrayList()
        topics.forEach { output.add(it.title) }
        return output
    }

    override fun getQuiz(title: String): ArrayList<QuizApp.Quiz> {
        return when (title) {
            "Math" -> topics[0].questions
            "Physics" -> topics[1].questions
            else -> topics[2].questions
        }
    }

    override fun getTopicDescription(topic: String): String {
        return when (topic) {
            "Math" -> topics[0].description
            "Physics" -> topics[1].description
            else -> topics[2].description
        }
    }



    val mathq1 = QuizApp.Quiz("4 * 5", arrayOf("12", "16", "20", "24"), 2)
    val mathq2 = QuizApp.Quiz("32 / 4", arrayOf("4", "6", "8", "10"), 2)
    val mathq3 = QuizApp.Quiz("Square root of 16", arrayOf("3", "4", "5", "6"), 1)

    val pq1 = QuizApp.Quiz(
        "Newton's third law of motion", arrayOf(
            "Every object in a state of uniform motion will remain in that state of motion unless an external force acts on it",
            "For every action there is an equal and opposite reaction",
            "Force equals mass times acceleration",
            "None of the above"
        ), 1
    )
    val pq2 = QuizApp.Quiz(
        "You drop a bowling ball, baseball, and golf ball from a two story building, which ball hits the ground first?",
        arrayOf("bowling ball", "baseball", "golf ball", "all hit the ground at the same time"), 3
    )
    val pq3 = QuizApp.Quiz(
        "Which of the following is an example of a vector quantity?",
        arrayOf("Temperature", "Velocity", "Volume", "Speed"),
        1
    )

    val marvelq1 = QuizApp.Quiz(
        "Which Super Hero is not apart of the marvel universe?",
        arrayOf("Iron Man", "Thor", "Ant Man", "Spider Man"),
        3
    )
    val marvelq2 = QuizApp.Quiz(
        "What color is the main villain in the Marvel Universe?",
        arrayOf("Magenta", "Blue", "Purple", "Cyan"),
        2
    )

    val mathTopic = QuizApp.Topic("Math", "The study of the measurement, relationships, and properties of quantities and sets, using numbers and symbols" ,
        arrayListOf(mathq1, mathq2, mathq3))

    val physicsTopic = QuizApp.Topic("Physics", "the science that deals with matter, energy, motion, and force",
        arrayListOf(pq1, pq2, pq3))

    val marvelTopic = QuizApp.Topic("Marvel", "A universe with superheroes based on the american made comic books",
        arrayListOf(marvelq1, marvelq2))

    public var topics = arrayListOf(mathTopic, physicsTopic, marvelTopic)
}