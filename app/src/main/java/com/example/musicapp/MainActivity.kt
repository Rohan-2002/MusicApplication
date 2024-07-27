package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
//                if the API call is a success then this method will be called
                val dataList = p1.body()?.data!!

//                val text = findViewById<TextView>(R.id.textView1)
//                text.text = dataList.toString()
                myAdapter = MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("onResponse", "onResponse" + p1.body())

            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
//                if the API call is a failure then this method will be called
                Log.d("onFailure", "onFailure" + p1.message)


            }
        })

    }
}