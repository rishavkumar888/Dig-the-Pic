package com.rishav.first_delete1

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class FlickrHolder(v:View):RecyclerView.ViewHolder(v){
    val thumbnail:ImageView=v.findViewById(R.id.thumbnail)
    val title:TextView=v.findViewById(R.id.title_name)
}

class FlickrAdapter(private var photolist:List<Photo>):RecyclerView.Adapter<FlickrHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.browse,parent,false)
        return FlickrHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrHolder, position: Int) {
        val photoitem=photolist[position]
        Picasso.get().load(photoitem.image).error(R.drawable.placeholder).placeholder(R.drawable.placeholder)
            .into(holder.thumbnail)
        holder.title.text=photoitem.author
    }

    override fun getItemCount(): Int {
        return if(photolist.isNotEmpty()) photolist.size else 0
    }
    fun newDataPosted(data:List<Photo>)
    {
        photolist= data
        notifyDataSetChanged()
    }
    fun getPhoto(position:Int):Photo?{
        return if(photolist.isNotEmpty()) photolist[position] else null
    }
}