package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ANSWER = "param1"
private const val GUESS = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnswerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnswerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AnswerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var guess: String = ""
    private var answer: String = ""
    private var soFar: Int = 0
    private var total: Int = 0
    private var correct: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guess = it.getString(GUESS)
            answer = it.getString(ANSWER)
            soFar = it.getInt("SO_FAR")
            total = it.getInt("TOTAL")
            correct = it.getInt("CORRECT")

        }
    }

    interface OnNextQuestionListener {
        fun onNextQuestion(soFar: Int, total: Int, correct: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.answer_view, container, false)

        var guessView: TextView = root.findViewById(R.id.yourResponse)
        guessView.text = guess

        var correctView: TextView = root.findViewById(R.id.correctAnswer)
        correctView.text = "dummy Value"



        var result: TextView = root.findViewById(R.id.result)
        if (guess === answer) {
            correct++
            result.text = "Correct!!"
        } else {
            result.text = "Incorrect..."
        }



        var score: TextView = root.findViewById(R.id.score)
        score.text = "You have answered $correct out of $soFar so far"

        var button: Button = root.findViewById(R.id.nextBtn)
        if (soFar >= total) {
            button.text = "Finish"
        }

        button.setOnClickListener {
            if (button.text === "Finish") {
                startActivity(Intent(activity, MainActivity()::class.java))
            } else {
                (activity as OnNextQuestionListener).onNextQuestion(soFar, total, correct)
            }
        }

        return root
    }


    companion object {
        @JvmStatic
        fun newInstance(guess: String, answer: String, total: Int, qsSoFar: Int, correct: Int) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putString(GUESS, guess)
                    putString(ANSWER, answer)
                    putInt("TOTAL", total)
                    putInt("SO_FAR", qsSoFar)
                    putInt("CORRECT", correct)
                }
            }
    }
}
