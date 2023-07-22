package com.darshandangar.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemclicked) : RecyclerView.Adapter<NewsViewHolder>() {
   private val iteam: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(iteam[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val currentIteam = iteam[position]
        holder.titleView.text = currentIteam.title
        holder.title.text = currentIteam.author
        Glide.with(holder.itemView.context).load(currentIteam.imageUrl).into(holder.image)
    }

    fun updatenews(updatedNews: ArrayList<News>){
        iteam.clear()
        iteam.addAll(updatedNews)

        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return iteam.size
    }
}

private operator fun Any.get(adapterPosition: Int) {

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val title: TextView = itemView.findViewById(R.id.title2)

}
interface NewsItemclicked{


    fun onItemClicked(iteam: News)


}