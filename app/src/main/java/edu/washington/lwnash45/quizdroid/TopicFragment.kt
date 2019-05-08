package edu.washington.lwnash45.quizdroid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 *
 */
class TopicFragment : Fragment() {

    companion object {
        const val TOPIC = "TOPIC"
        fun newInstance(title: String): TopicFragment {

            var args = Bundle().apply {
                putString(TOPIC, title)
            }

            val theFragment = TopicFragment()
            theFragment.arguments = args

            return theFragment
        }
    }

    interface BeginQuizListener {
        fun onBeginQuiz(topic: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.topic_overview, container, false)


        val title = arguments?.let {
            it.getString(TOPIC)
        }

        var titleView: TextView = root.findViewById(R.id.topicTitle)
        titleView.text = title

        var descriptionView: TextView = root.findViewById(R.id.topicDes)

        descriptionView.text = QuizApp.topicRepository.getTopicDescription(title!!)


        root.findViewById<View>(R.id.beginButton).setOnClickListener {
            (activity as BeginQuizListener).onBeginQuiz(title!!)
        }

        return root
    }

}
