package br.com.gitapp.domian.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class ImageLoader(private var context: Context) {

    fun loadCircled(path: String, imageView: ImageView) {
        Glide.with(context)
            .load(path)
            .transform(CircleCrop())
            .into(imageView)
    }

    fun load(path: String, imageView: ImageView) {
        Glide.with(context)
            .load(path)
            .into(imageView)
    }
}