
package com.rishav.first_delete1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity:BaseActivity(),GetRawData.OnDataComplete,GetJsonData.OnDataAvailable,
        Recycleritemclicklistener.OnClickListenerFunction
{
    val TAG="main"
    val flickradapter=FlickrAdapter(ArrayList())
//    var getrawdata=GetRawData(this)
//    val getjsondata=GetJsonData(this)
    override fun onCreate(savedInstanceState:Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activateToolbar(false)
        val recycler_view:RecyclerView=findViewById(R.id.recycler_view)
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(Recycleritemclicklistener(this,recycler_view,this))
        recycler_view.adapter=flickradapter

    }
    fun createURL(baseURL:String,tag:String,lang:String,matchALL:Boolean):String
    {
        return Uri.parse(baseURL).buildUpon().appendQueryParameter("tags",tag)
            .appendQueryParameter("tagmode",if(matchALL) "ALL" else "ANY")
            .appendQueryParameter("lang",lang)
            .appendQueryParameter("format","json")
            .appendQueryParameter("nojsoncallback","1")
            .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_search->{
                startActivity(Intent(this,SearchAvtivity::class.java))
                return true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedpref= PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val queryResult=sharedpref.getString(FLICKR_QUERRY,"").toString()

            if(queryResult.isNotEmpty()) {
                val url=createURL("https://www.flickr.com/services/feeds/photos_public.gne",queryResult,"en-us",true)
                val getrawdata=GetRawData(this)
                getrawdata.execute(url)
            }

    }
//    override fun onDestroy() {
//        super.onDestroy()
//        getrawdata.cancel(true)
//    }

    override fun OnNormalClick(view: View, position: Int) {
        Log.d(TAG,">>>>>>>>>>>> normal click")
        Toast.makeText(this,"short chick",Toast.LENGTH_SHORT).show()
    }

    override fun OnNormalLongclick(view: View, position: Int) {
        Log.d(TAG,">>>>>>>>>>>>>normal long click")
        Toast.makeText(this,"long click",Toast.LENGTH_SHORT).show()
        val photo=flickradapter.getPhoto(position)
        if(photo!=null)
        {
            val intent= Intent(this,PhotoDetailActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER,photo)
            startActivity(intent)
        }
    }

    override fun ondatacomplete(result:String,status:DownloadStatus)
    {
        if(status==DownloadStatus.OK)
        {
            val getjsondata=GetJsonData(this)
            getjsondata.execute(result)
        }
    }
    override fun error(e:Exception)
    {
        Log.d("message",">>>>>>>>>>>>>>>>>>>>>>>>> error getjsondata ${e.message}")
    }
    override fun ondataavailable(data:List<Photo>)
    {
        flickradapter.newDataPosted(data)
    }
}