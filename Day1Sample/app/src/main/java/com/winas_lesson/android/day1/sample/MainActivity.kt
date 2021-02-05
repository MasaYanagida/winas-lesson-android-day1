package com.winas_lesson.android.day1.sample

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.winas_lesson.android.day1.sample.databinding.ActivityMainBinding
import com.winas_lesson.android.day1.sample.databinding.ContentItemViewBinding
import timber.log.Timber
import kotlin.properties.Delegates

class MainActivity : AbstractActivity() {
    private lateinit var binding: ActivityMainBinding
    //private val adapter by lazy { ContentListAdapter(this) }
    // https://developer.android.com/topic/libraries/view-binding
    private val recyclerView: RecyclerView
        get() = binding.recyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var contents: ArrayList<Content> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        reload()
    }

    override fun onResume() {
        super.onResume()
        // TODO
    }

    private fun reload() {
        contents = arrayListOf()
        for (i in 1..10) {
            contents.add(Content.create())
        }
        //adapter.contents = contents
    }
}
