package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        toolbar.title = "Preference Settings"

        val urlString = getSharedPreferences("URL", Context.MODE_PRIVATE)
        val url = findViewById<View>(R.id.dataUrl) as TextView
        url.text = "Data URL: $urlString"

        val update = findViewById<View>(R.id.updates) as TextView
        update.text = "Check for updates like once a month"

    }

}
