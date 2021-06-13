package com.rishav.first_delete1

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject

class GetJsonData(private val listener:OnDataAvailable):AsyncTask<String,Void,ArrayList<Photo>>()
{
    interface OnDataAvailable{
        fun ondataavailable(list:List<Photo>)
        fun error(e:Exception)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        val photolist=ArrayList<Photo>()
        try{
            val initial=JSONObject(params[0])
            val list=initial.getJSONArray("items")
            for(i in 0 until list.length())
            {
                val h=list.getJSONObject(i)
                val title=h.getString("title")
                val author=h.getString("author")
                val authorID=h.getString("author_id")
                val tag=h.getString("tags")
                val media=h.getJSONObject("media")
                val image=media.getString("m")
                val link=image.replaceFirst("_m.jpg","_b.jpg")
                val phototobject=Photo(title,author,authorID,tag,link,image)
                photolist.add(phototobject)
            }
        }catch(e:JSONException)
        {
            e.printStackTrace()
            cancel(true)
            listener.error(e)
        }
        return photolist
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        super.onPostExecute(result)
        listener.ondataavailable(result)
    }
}