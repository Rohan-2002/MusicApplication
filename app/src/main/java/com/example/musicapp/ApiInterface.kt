package com.example.musicapp

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.Call as Call1

interface ApiInterface {

    @Headers("X-RapidAPI-Key: b7c2504984mshcc296b5fadad5e7p1918e0jsn3d95d2d4bb98",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call1<MyData>

}