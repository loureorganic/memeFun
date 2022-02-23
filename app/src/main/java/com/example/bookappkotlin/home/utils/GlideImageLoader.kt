package com.example.bookappkotlin.home.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader() {

    fun loaderImage(context: Context, imageData: String?, holderImage: ImageView) {
        Glide.with(context).load(imageData).into(holderImage)
    }
}