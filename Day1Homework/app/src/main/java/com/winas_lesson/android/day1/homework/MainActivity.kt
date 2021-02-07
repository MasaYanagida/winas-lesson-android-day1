package com.winas_lesson.android.day1.homework

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.winas_lesson.android.day1.homework.databinding.ActivityMainBinding
import com.winas_lesson.android.day1.homework.views.ContentItemViewHolder
import kotlin.properties.Delegates

class MainActivity : AbstractActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ContentListAdapter() }
    private val recyclerView: RecyclerView
        get() = binding.recyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private val contents: ArrayList<Content> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        if (isViewLoaded.not()) {
            return
        }

        reload()
    }

    private fun reload() {
        contents.clear()
        for (index in 0..10) {
            val content = Content.create()
            contents.add(content)
        }
        adapter.contents = contents
    }

    inner class ContentListAdapter : RecyclerView.Adapter<ContentItemViewHolder>() {

        var contents: MutableList<Content> by Delegates.observable(mutableListOf()) { _, _, _ ->
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
            return ContentItemViewHolder.create(parent)
        }

        override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
            val content = contents[position]
            holder.content = content
        }

        override fun getItemCount(): Int {
            return contents.size
        }
    }
}
