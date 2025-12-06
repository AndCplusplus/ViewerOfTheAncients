package com.example.vieweroftheancients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vieweroftheancients.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var heroAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        heroAdapter = HeroAdapter()
        binding.heroRecyclerView.adapter = heroAdapter
        binding.heroRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchHeroData()
    }

    private fun fetchHeroData() {
        val client = AsyncHttpClient()
        client.get("https://api.opendota.com/api/heroes", object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {
                val heroes = Gson().fromJson(responseString, Array<Hero>::class.java).toList()
                heroAdapter.submitList(heroes)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                // Handle error
            }
        })
    }
}