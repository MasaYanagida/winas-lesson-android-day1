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
    private val adapter by lazy { ContentListAdapter(this) }
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

        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        reload()
    }

    override fun onResume() {
        super.onResume()
        if (!isViewLoaded || 0 >= lastPauseTime) {
            return
        }
        val currentTime = (System.currentTimeMillis() / 1000L).toInt()
        val interval = currentTime - lastPauseTime
        Timber.d("Resumable onResume!! interval= ${interval}")

        if (5 > interval) {
            // only update recyclerView if interval under 5 seconds
            adapter.notifyDataSetChanged()
        } else {
            // reload view if interval over 5 seconds
            reload()
        }
        lastPauseTime = 0
    }

    private fun reload() {
        contents = arrayListOf()
        for (i in 1..10) {
            contents.add(Content.create())
        }
        adapter.contents = contents
    }
}

class ContentListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var contents: MutableList<Content> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = ContentItemViewHolder.create(parent)
        return holder
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ContentItemViewHolder)?.content = contents[position]
    }
    override fun getItemCount(): Int {
        return contents.size
    }
    override fun getItemViewType(position: Int): Int {
        return 0
    }
}

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: ContentItemViewBinding
    companion object {
        fun create(parent: ViewGroup): ContentItemViewHolder {
            // snake_case, UpperCamelCase, lowerCamelCase
            val viewBinding = ContentItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            val holder = ContentItemViewHolder(viewBinding.root)
            holder.binding = viewBinding
            return holder
        }
    }
    var content: Content by Delegates.observable(Content()) { prop, _, _ ->
        updateView()
    }
    private fun updateView() {
        binding.nameLabel.text = content.name
        binding.addressLabel.text = content.address
    }
}
