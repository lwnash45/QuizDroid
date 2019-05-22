package edu.washington.lwnash45.quizdroid

import android.app.DownloadManager
import android.app.IntentService
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.os.IBinder
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.net.HttpURLConnection


class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("working", "please")
        AsyncDataFetch().execute(intent!!.getStringExtra("url"))
    }

    inner class AsyncDataFetch: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            val jsonText: String


            val conn = java.net.URL(params[0]).openConnection() as HttpURLConnection
            try {
                conn.connect()
                Log.d("CheckDownload", "Downloaded")
                jsonText = conn.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                conn.disconnect()
            }

            return jsonText
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val out: PrintWriter
            try {
                val dir = Environment.getExternalStorageDirectory()
                if (!dir.exists()) { dir.mkdirs() } // make dir if doesn't otherwise exist (pre-19)
                val file = File(dir, "questions.json")

                val out = PrintWriter(FileWriter(file, true))
                out.println(result)
                out.close()
            } catch (ioe: IOException) {
                ioe.printStackTrace()
            }
        }
    }
}