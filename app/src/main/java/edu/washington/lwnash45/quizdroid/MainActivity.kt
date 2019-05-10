package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.answer_view.view.*
import org.json.JSONObject
import java.io.IOException

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
