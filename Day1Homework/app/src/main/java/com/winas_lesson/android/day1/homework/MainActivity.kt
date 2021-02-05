package com.winas_lesson.android.day1.homework

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.winas_lesson.android.day1.homework.databinding.ActivityMainBinding

class MainActivity : AbstractActivity() {
    private lateinit var binding: ActivityMainBinding
    // TODO : adapter
    //private val adapter by lazy { ContentListAdapter(this) }
    private val recyclerView: RecyclerView
        get() = binding.recyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var contents: ArrayList<Content> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // TODO
        //recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        reload()
    }

    override fun onResume() {
        super.onResume()
        // TODO : implement this
    }

    private fun reload() {
        // TODO
        //contents = ....
        //adapter.contents = contents
    }
}
