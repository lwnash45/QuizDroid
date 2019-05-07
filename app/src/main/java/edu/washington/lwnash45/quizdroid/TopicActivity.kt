package edu.washington.lwnash45.quizdroid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

class TopicActivity(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)

        var title = this.intent.getStringExtra("TOPIC")

        var titleView: TextView = findViewById(R.id.topicTitle)
        titleView.text = title

        var descriptionView: TextView = findViewById(R.id.topicDes)
        descriptionView.text = when (title) {
            "Math" -> "The study of the measurement, relationships, and properties of quantities and sets, using numbers and symbols. Arithmetic, algebra, geometry, and calculus are branches of mathematics."
            "Physics" -> "the science that deals with matter, energy, motion, and force."
            else -> "A universe with superheroes based on the american made comic books"
        }

        findViewById<View>(R.id.beginButton).setOnClickListener {
            var intent = Intent(this@TopicActivity, QuizActivity()::class.java)
            intent.putExtra("TOPIC", title)
            intent.putExtra("TOTAL_QS", 3)
            startActivity(intent)
        }
    }
}