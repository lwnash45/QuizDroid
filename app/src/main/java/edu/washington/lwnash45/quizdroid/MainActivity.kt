package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = applicationContext.getSharedPreferences("URL", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("URL", "http://tednewardsandbox.site44.com/questions.json")
        editor.putString("UPDATES", "You don't even have to check again I doubt I'll make any updates to this app")


        var topicNames = QuizApp.topicRepository.getTopicNames()


        val topicListView = findViewById<ListView>(R.id.topicsListView)
        val adapter: ListAdapter = ButtonAdapter(this, topicNames)
        topicListView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        return true
    }

}
