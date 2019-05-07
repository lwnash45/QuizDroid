package edu.washington.lwnash45.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class AnswerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer_view)

        var guess: TextView = findViewById(R.id.yourResponse)
        guess.text = this.intent.getStringExtra("GUESS")

        var correct: TextView = findViewById(R.id.correctAnswer)
        correct.text = this.intent.getStringExtra("CORRECT")

        var questionsSoFar = this.intent.getIntExtra("NUM_QS", 0)
        var totalCorrect = this.intent.getIntExtra("NUM_CORRECT", 0)
        var totalQs = this.intent.getIntExtra("TOTAL_QS", 0)


        var result: TextView = findViewById(R.id.result)
        if (guess != null && correct != null && guess.text === correct.text) {
            totalCorrect++
            result.text = "Correct!!"
        } else {
            result.text = "Incorrect..."
        }

        var score: TextView = findViewById(R.id.score)
        score.text = "You have answered $totalCorrect out of $questionsSoFar so far"

        var button: Button = findViewById(R.id.nextBtn)
        if (questionsSoFar >= totalQs) {
            button.text = "Finish"
        }

        button.setOnClickListener {
            if (questionsSoFar >= totalQs) {
                startActivity(Intent(this@AnswerActivity, MainActivity()::class.java))
            } else {
                var intent = Intent(this@AnswerActivity, QuizActivity()::class.java)
                intent.putExtra("NUM_CORRECT", totalCorrect)
                intent.putExtra("TOTAL_QS", totalQs)
                intent.putExtra("NUM_QS", questionsSoFar)
                startActivity(intent)
            }
        }



    }
}