package com.rishav.first_delete1

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


internal const val FLICKR_QUERRY="FLICKR_QUERRY"
internal const val PHOTO_TRANSFER="PHOTO_TRANSFER"
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    internal fun activateToolbar(enableHome:Boolean)
    {
//        var toolbar=findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}