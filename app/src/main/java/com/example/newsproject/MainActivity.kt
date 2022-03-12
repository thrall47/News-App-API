package com.example.newsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    // https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=8a9389bc605243e0bba3d6ddfed96414

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNews()

        val mAdView: AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    fun loadNews(){
        val pb: ProgressBar = findViewById(R.id.pb)
        pb.visibility = View.VISIBLE
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cat = intent.getStringExtra("cat")
        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val code = pref.getString("code", "de")
        val callable = retrofit.create(CallableInterface::class.java)
        val getNewsCall = callable.getNews(cat, code)
        getNewsCall.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                pb.visibility = View.GONE // or View.invisable
                val news: News? = response.body()
                val articles = news?.articles
                showNews(articles)
                Log.d("json", articles.toString())
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                pb.visibility = View.GONE
                Log.d("json", "${t.localizedMessage} ")
            }
        })
    }
    fun showNews(articles: ArrayList<Article>?){
        val rv: RecyclerView = findViewById(R.id.rv)
        val manager = LinearLayoutManager(this)
        rv.layoutManager = manager
        val adapter = NewsAdapter(this, articles)
        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.refresh, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        loadNews()

        return super.onOptionsItemSelected(item)
    }


}

