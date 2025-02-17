package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//Adapter connects the user interface with the database

class MyAdapter(private val context : Activity, private val dataList : List<Data>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Populate the data into the view

        val currentData = dataList[position]

        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())

        holder.title.text = currentData.title

        Picasso.get().load(currentData.album.cover).into(holder.musicImg)

        holder.play.setOnClickListener {
            mediaPlayer.start()
        }

        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        // Create the view in case the layout manager fails to create view for the data
        val musicImg : ImageView
        val title : TextView
        val play : ImageButton
        val pause : ImageButton

        init{
            musicImg = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTitle)
            play = itemView.findViewById(R.id.playBtn)
            pause = itemView.findViewById(R.id.pauseBtn)
        }


    }

}