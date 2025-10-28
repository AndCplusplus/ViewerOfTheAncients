package com.example.vieweroftheancients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vieweroftheancients.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroButton.setOnClickListener {
            fetchHeroData()
        }
    }

    private fun fetchHeroData() {
        val client = AsyncHttpClient()
        client.get("https://api.opendota.com/api/heroes", object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {
                val heroes = Gson().fromJson(responseString, Array<Hero>::class.java).toList()
                val randomHero = heroes.random()

                binding.heroName.text = randomHero.localizedName
                binding.heroAttribute.text = "Primary Attribute: ${randomHero.primaryAttr}"
                binding.heroRoles.text = "Roles: ${randomHero.roles.joinToString()}"
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                binding.heroName.text = "Error"
            }
        })
    }
}