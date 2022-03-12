package com.example.newsproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val activity: Activity, val articles: ArrayList<Article>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv_news)
        val image: ImageView = view.findViewById(R.id.iv_news)
        val parent: CardView = view.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val view =
            activity.layoutInflater.inflate(R.layout.news_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.text.text = articles?.get(position)?.title
        val picture = articles?.get(position)?.urlToImage

        Glide
            .with(activity)
            .load(picture)
            .into(holder.image)

        holder.parent.setOnClickListener {
            val link = Uri.parse(articles?.get(position)?.url)
            val intent = Intent(Intent.ACTION_VIEW, link)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount() = articles?.size ?: 0
    // counts how many items it will work on for the View

}