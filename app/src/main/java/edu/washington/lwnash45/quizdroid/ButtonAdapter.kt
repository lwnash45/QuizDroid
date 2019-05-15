package edu.washington.lwnash45.quizdroid

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class ButtonAdapter(context: Context, private val topics: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.topics_button_view, topics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var root = LayoutInflater.from(this.context).inflate(R.layout.topics_button_view, parent, false)
        val button = root.findViewById<Button>(R.id.button)
        val buttonString = this.topics[position]
        button.text = buttonString
        button.setOnClickListener {
            var intent: Intent = Intent(this.context, FragmentedActivity()::class.java)
            intent.putExtra("TOPIC", buttonString)
            startActivity(this.context, intent, intent.extras)
        }
        return root
    }
}

