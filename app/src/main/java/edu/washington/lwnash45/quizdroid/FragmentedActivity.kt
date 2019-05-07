package edu.washington.lwnash45.quizdroid

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_fragmented.*

class FragmentedActivity : AppCompatActivity(), TopicFragment.BeginQuizListener, QuizFragments.OnAnswerListener, AnswerFragment.OnNextQuestionListener {

    override fun onBeginQuiz(total: Int) {
        val quiz = QuizFragments.newInstance(total, 0, 0)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, quiz, "BEGIN QUIZ").commit()
    }

    override fun onAnswer(guess: String, answer: String, correct: Int, soFar: Int, total: Int) {
        val answer = AnswerFragment.newInstance(guess, answer, total, soFar, correct)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, answer, "NEXT").commit()

    }

    override fun onNextQuestion(soFar: Int, total: Int, correct: Int) {
        val question = QuizFragments.newInstance(total, soFar, correct)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, question, "QUESTION").commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmented)

        //val fragManager: TopicFragment = supportFragmentManager.findFragmentById(R.id.topicFragment) as TopicFragment

        var topicFragment = TopicFragment.newInstance(this.intent.getStringExtra("TOPIC"))

        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, topicFragment, "TOPIC_FRAGMENT").commit()
    }

}
