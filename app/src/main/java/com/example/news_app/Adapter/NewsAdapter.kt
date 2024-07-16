package com.example.news_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.ArticlesItem
import com.example.news_app.MainActivity
import com.example.news_app.R
import com.example.news_app.databinding.NewsviewBinding

class NewsAdapter( val newsList: List<ArticlesItem?>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var bindings = NewsviewBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.newsview,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList!!.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.bindings.title.setText("${newsList.get(position)?.title}")
        holder.bindings.discription.setText("${newsList.get(position)?.description}")
        Glide.with(holder.itemView.context).load(newsList!![position]!!.urlToImage).placeholder(R.drawable.ic_launcher_background).into(holder.bindings.newsimg)

    }

}