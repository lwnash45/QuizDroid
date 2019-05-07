package edu.washington.lwnash45.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toActivity(view: View) {
        var button: Button = view as Button
        var intent = Intent(this@MainActivity, FragmentedActivity()::class.java)
        Log.d("please", "work")
        intent.putExtra("TOPIC", button.text)
        startActivity(intent)
    }
}
