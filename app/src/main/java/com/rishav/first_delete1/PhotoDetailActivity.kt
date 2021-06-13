package com.rishav.first_delete1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PhotoDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        activateToolbar(true)
        val photo=intent.extras?.getParcelable<Photo>(PHOTO_TRANSFER) as Photo
        val author: TextView =findViewById(R.id.image_author)
        val photo_title:TextView=findViewById(R.id.photo_title)
        val photo_tag:TextView=findViewById(R.id.photo_tag)

        author.text=resources.getString(R.string.photo_author,photo.author)
        photo_title.text=resources.getString(R.string.photo_title,photo.title)
        photo_tag.text=resources.getString(R.string.photo_tag,photo.tags)
        val photo_image: ImageView =findViewById(R.id.photo_image)
        Picasso.get().load(photo.link).error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(photo_image)
    }
}