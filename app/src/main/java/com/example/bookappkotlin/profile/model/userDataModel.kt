package com.example.bookappkotlin.profile.model

import com.example.bookappkotlin.home.model.Meme
import com.example.bookappkotlin.home.model.MemeValue
import com.google.gson.annotations.SerializedName

data class userData(
    @SerializedName("data")
    val data: userValue? = null
)

data class userValue(
    @SerializedName("memes")
    val meme: List<Meme>? = null
)

