package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            totalQuestions = it.getInt(TOTAL)
            questionsSoFar = it.getInt(SO_FAR) + 1
            correct = it.getInt(CORRECT)
        }
    }

    interface OnAnswerListener {
        fun onAnswer(guess: String, answer: String, correct: Int, soFar: Int, total: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.quiz_view, container, false)

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
            (activity as OnAnswerListener).onAnswer(selectedAnswer, "", correct, questionsSoFar, totalQuestions)
        }

        return root
    }


    companion object {
        @JvmStatic
        fun newInstance(total: Int, soFar: Int, correct: Int) =
            QuizFragments().apply {
                arguments = Bundle().apply {
                    putInt(TOTAL, total)
                    putInt(SO_FAR, soFar)
                    putInt(CORRECT, correct)
                }
            }
    }
}
