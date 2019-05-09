package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TOTAL = "TOTAL"
private const val SO_FAR = "SO_FAR"
private const val CORRECT = "CORRECT"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuizFragments.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuizFragments.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuizFragments : Fragment() {
    // TODO: Rename and change types of parameters
    private var totalQuestions: Int = 0
    private var questionsSoFar: Int = 0
    private var correct: Int = 0
    private var topic: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionsSoFar = it.getInt(SO_FAR)
            correct = it.getInt(CORRECT)
            topic = it.getString("TOPIC")
        }
    }

    interface OnAnswerListener {
        fun onAnswer(guess: String, correct: Int, soFar: Int, topic: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.quiz_view, container, false)

        val questions: List<QuizApp.Quiz> = QuizApp.topicRepository.getQuiz(this.topic)!!
        totalQuestions = questions.size
        val question: QuizApp.Quiz = questions[questionsSoFar]

        var questionView: TextView = root.findViewById(R.id.questionText)
        questionView.text = question.question

        val answer1: RadioButton = root.findViewById(R.id.answer1)
        val answer2: RadioButton = root.findViewById(R.id.answer2)
        val answer3: RadioButton = root.findViewById(R.id.answer3)
        val answer4: RadioButton = root.findViewById(R.id.answer4)
        val answers = arrayOf(answer1, answer2, answer3, answer4)

        answers.forEachIndexed{index, it -> it.text = question.options[index]}

        var submitButton: Button = root.findViewById(R.id.submitBtn)

        var selectedAnswer = ""

        fun onSelectAnswer(view: RadioButton) {
            view.setOnClickListener {
                submitButton.visibility = View.VISIBLE
                selectedAnswer = view.text.toString()
            }
        }
        onSelectAnswer(root.findViewById(R.id.answer1))
        onSelectAnswer(root.findViewById(R.id.answer2))
        onSelectAnswer(root.findViewById(R.id.answer3))
        onSelectAnswer(root.findViewById(R.id.answer4))



        submitButton.setOnClickListener {
            (activity as OnAnswerListener).onAnswer(selectedAnswer, correct, questionsSoFar, topic)
        }
        return root
    }


    companion object {
        @JvmStatic
        fun newInstance(soFar: Int, correct: Int, topic: String) =
            QuizFragments().apply {
                arguments = Bundle().apply {
                    putInt(SO_FAR, soFar)
                    putInt(CORRECT, correct)
                    putString("TOPIC", topic)
                }
            }
    }
}
