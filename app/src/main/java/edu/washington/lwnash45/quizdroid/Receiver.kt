package edu.washington.lwnash45.quizdroid

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log

class CustomReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("CheckDownload", "Downloaded")
        val downloadURI = Uri.parse(intent!!.getStringExtra("url"))
        val request = DownloadManager.Request(downloadURI)
        val downloadManager = context!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        request.setTitle("Download")
        request.setDescription("Downloading file . . .")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "questions.json")
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager.enqueue(request)
    }
}