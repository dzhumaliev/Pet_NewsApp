package com.io.pet_newsapp.ui.news.fragments.server

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.io.pet_newsapp.R
import com.io.pet_newsapp.databinding.ItemNewsBinding
import com.io.pet_newsapp.ui.news.entity.ArticlesUI

class NewsAdapter : PagingDataAdapter<ArticlesUI, NewsAdapter.NewsVH>(NewsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        return NewsVH(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class NewsDiffCallBack : DiffUtil.ItemCallback<ArticlesUI>() {
        override fun areItemsTheSame(oldItem: ArticlesUI, newItem: ArticlesUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticlesUI, newItem: ArticlesUI): Boolean {
            return oldItem == newItem
        }
    }


    class NewsVH(
        binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val image = itemView.findViewById<ImageView>(R.id.img)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        private val tvDate = itemView.findViewById<TextView>(R.id.tv_date)

        @SuppressLint("SetTextI18n")
        fun bind(item: ArticlesUI) {
            tvTitle.text = item.title
            tvDesc.text = item.description
            tvDate.text =
                itemView.context.getString(R.string.date_publication) + " " + item.publishedAt
            Glide.with(itemView.context)
                .load(item.urlToImage)
                .placeholder(R.drawable.img_preface)
                .into(image)
        }
    }
}