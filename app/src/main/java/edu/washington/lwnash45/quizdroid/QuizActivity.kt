package edu.washington.lwnash45.quizdroid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import kotlinx.android.synthetic.main.quiz_view.*

class QuizActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_view)

        var intent = Intent(this@QuizActivity, AnswerActivity()::class.java)

        var submitButton: Button = findViewById(R.id.submitBtn)

        var selectedAnswer = ""

        fun onSelectAnswer(view: RadioButton) {
            view.setOnClickListener {
                submitButton.visibility = View.VISIBLE
                selectedAnswer = view.text.toString()
            }
        }
        onSelectAnswer(findViewById(R.id.answer1))
        onSelectAnswer(findViewById(R.id.answer2))
        onSelectAnswer(findViewById(R.id.answer3))
        onSelectAnswer(findViewById(R.id.answer4))

        Log.d("total", this.intent.getIntExtra("TOTAL_QS", 0).toString())

        submitButton.setOnClickListener {
            intent.putExtra("GUESS", selectedAnswer)
            intent.putExtra("CORRECT", "dumby value")
            intent.putExtra("NUM_QS", this.intent.getIntExtra("NUM_QS", 0) + 1)
            intent.putExtra("NUM_CORRECT", this.intent.getIntExtra("NUM_CORRECT", 0))
            intent.putExtra("TOTAL_QS", this.intent.getIntExtra("TOTAL_QS", 0))
            startActivity(intent)
        }

    }

}