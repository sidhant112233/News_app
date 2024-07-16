package com.example.news_app

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.news_app.Adapter.NewsAdapter
import com.example.news_app.Client.ApiClient
import com.example.news_app.Interface.ApiInterface
import com.example.news_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var mainbimding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var apiModel: ApiModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainbimding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbimding.root)


        NewsApiCalling("us", "business")
        countryClick()
        navigationView()

    }

    private fun navigationView() {
        mainbimding.menu.setOnClickListener {
            mainbimding.main.openDrawer(Gravity.RIGHT)

            mainbimding.navigation.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.setting -> {
                        Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun countryClick() {
        mainbimding.btnin.setOnClickListener {
            NewsApiCalling("in", "business")
        }
        mainbimding.btnus.setOnClickListener {
            NewsApiCalling("us", "business")
        }
        mainbimding.btnuk.setOnClickListener {
            NewsApiCalling("jp", "business")
        }

    }

    fun RvSetup(newslist: List<ArticlesItem?>?) {

        var adapter = newslist?.let { NewsAdapter(it) }
        mainbimding.RvData.adapter = adapter
    }

    fun NewsApiCalling(country: String, category: String) {
        var apiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)
        apiInterface?.getnews(country, category)?.enqueue(object : Callback<ApiModel> {
            override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                var apiModel = response.body()
                Log.i("Tag", "onResponce: ${apiModel?.articles?.get(0)?.title}")

                RvSetup(apiModel?.articles)

            }

            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                Log.e("Error", "onFailure:${t.message}")
            }

        })


    }

}