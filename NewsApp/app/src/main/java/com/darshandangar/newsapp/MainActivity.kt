package com.darshandangar.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import java.lang.reflect.Array

class MainActivity : AppCompatActivity(), NewsItemclicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView
        recyclerView = findViewById(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter (this)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {
        val url =
            "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=da8de111f8cc4ddd9d3e647b62e20d74"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val newsJSONArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJSONArray.length()) {
                    val newsJSONObject = newsJSONArray.getJSONObject(i)
                    val news = News(
                        newsJSONObject.getString("title"),
                        newsJSONObject.getString("author"),
                        newsJSONObject.getString("url"),
                        newsJSONObject.getString("imageurl")
                    )
                    newsArray.add(news)
                }
                mAdapter.updatenews(newsArray)
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                Toast.makeText(this,"Something went wrong..",Toast.LENGTH_SHORT).show()
            }
        )

// Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


        override fun onItemClicked(iteam: News) {
            //TODO("Not yet implemented")
//        Toast.makeText(this,"Clicked Iteam is $iteam",Toast.LENGTH_SHORT).show()
        }

}