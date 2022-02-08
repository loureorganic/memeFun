package com.example.bookappkotlin.home.model

import com.google.gson.annotations.SerializedName

data class MemeResponse(
    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("data")
    val data: MemeValue? = null
)

data class MemeValue(
    @SerializedName("memes")
    val meme: List<Meme>? = null
)

data class Meme(
    @SerializedName("box_count")
    val boxCount: Int? = null,

    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("width")
    val width: Int? = null
)