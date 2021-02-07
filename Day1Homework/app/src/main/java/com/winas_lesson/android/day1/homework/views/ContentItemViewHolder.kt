package com.winas_lesson.android.day1.homework.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.winas_lesson.android.day1.homework.Content
import com.winas_lesson.android.day1.homework.databinding.ContentItemViewBinding
import kotlin.properties.Delegates

class ContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var binding: ContentItemViewBinding

    var content: Content by Delegates.observable(Content()) { _, _, _ ->
        updateView()
    }

    private fun updateView() {
        binding.nameLabel.text = content.name
        binding.addressLabel.text = content.address
    }

    companion object {
        fun create(parent: ViewGroup): ContentItemViewHolder {
            val viewBinding = ContentItemViewBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            val holder = ContentItemViewHolder(viewBinding.root)
            holder.binding = viewBinding
            return holder
        }
    }
}