package com.darshandangar.mymemesapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    lateinit var imageView : ImageView
    lateinit var progressBar : ProgressBar
    var currentimg : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.imageView)
        progressBar = findViewById(R.id.progressbar)


    }

    fun loadmeme(){
        progressBar.visibility = View.VISIBLE
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        // Instantiate the cache
//        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
//
//// Set up the network to use HttpURLConnection as the HTTP client.
//        val network = BasicNetwork(HurlStack())

// Instantiate the RequestQueue with the cache and network. Start the queue.
//        val requestQueue = RequestQueue(cache, network).apply {
//            start()
//        }
        val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                currentimg = response.getString("url")
                Glide.with(this)
                    .load(currentimg)
                    .listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                             //TODO("Not yet implemented")
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                               //TODO("Not yet implemented")
                            progressBar.visibility = View.GONE
                            return false
                        }

                    })
                    .into(imageView)
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun sharebtn(view: View) {
        val intent : Intent = Intent(Intent.ACTION_SEND)
        //val intent : Intent
        //;(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey,checkout this cool meme I got from Reddit $currentimg")
        val chooser = Intent.createChooser(intent,"Share this Meme using...")
        startActivity(chooser)
       // startActivity(intent)
    }
    fun nextbtn(view: View) {
        loadmeme()
    }

}