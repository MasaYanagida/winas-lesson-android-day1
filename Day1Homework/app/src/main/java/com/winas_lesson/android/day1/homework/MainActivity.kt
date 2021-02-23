package com.winas_lesson.android.day1.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.winas_lesson.android.day1.homework.databinding.ActivityMainBinding
import com.winas_lesson.android.day1.homework.databinding.ContentItemViewBinding
import kotlin.properties.Delegates
import android.view.View
import timber.log.Timber

class MainActivity : AbstractActivity() {
    private lateinit var binding: ActivityMainBinding
    // TODO : adapter
    private val adapter by lazy { ContentListAdapter(this) }
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
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        reload()
    }

    override fun onResume() {
        super.onResume()
        // TODO : implement this
        super.onResume()
        if(!isViewLoaded || 0 >= lastPauseTime){
            return
        }
        val currentTime:Int = (System.currentTimeMillis()/1000L).toInt()
        val interval:Int = currentTime - lastPauseTime
        Timber.d( "Resumable onResume!! interval = ${interval}")
        if(interval < 10 ){
            adapter.notifyDataSetChanged()
        }else{
            reload()
        }
        lastPauseTime = 0
    }

    private fun reload() {
        // TODO
        //contents = ....
        contents = arrayListOf()
        for(i in 1..10){
            contents.add(Content.create())
        }
        adapter.contents = contents
    }
}

class ContentListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var contents: MutableList<Content> by Delegates.observable(arrayListOf()){ _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder : ContentItemViewHolder = ContentItemViewHolder.create(parent)
        return holder;
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ContentItemViewHolder)?.content = contents[position]
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