package edu.washington.lwnash45.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.answer_view.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var topicNames = QuizApp.topicRepository.getTopicNames()
        var mathBtn: TextView = findViewById<View>(R.id.mathBtn) as TextView
        mathBtn.text = topicNames[0]
        var pBtn: TextView = findViewById<View>(R.id.physicsBtn) as TextView
        pBtn.text = topicNames[1]
        var mbtn: TextView = findViewById<View>(R.id.marvelBtn) as TextView
        mbtn.text = topicNames[2]
    }

    fun toActivity(view: View) {
        var button: Button = view as Button
        var intent = Intent(this@MainActivity, FragmentedActivity()::class.java)
        Log.d("please", "work")
        intent.putExtra("TOPIC", button.text)
        startActivity(intent)
    }
}
