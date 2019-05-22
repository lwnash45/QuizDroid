package edu.washington.lwnash45.quizdroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        toolbar.title = "Preference Settings"

        val urlString = intent.getStringExtra("url")
        val url = findViewById<View>(R.id.dataUrl) as TextView
        url.text = "Data URL: $urlString"

        val update = findViewById<View>(R.id.updates) as TextView
        update.text = "Check for updates like once a month"

        val updateBtn = findViewById<Button>(R.id.update)
        updateBtn.setOnClickListener {
            Log.d("please", "work")
            var alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alert = Intent(this, Receiver::class.java)
            alert.putExtra("url", urlString.toString())
            val alarmIntent = PendingIntent.getBroadcast(this, 0, alert, PendingIntent.FLAG_UPDATE_CURRENT)
            val interval = (60000).toLong()
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, alarmIntent)
        }

        findViewById<Button>(R.id.back).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}
