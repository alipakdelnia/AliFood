package com.example.alifood

import android.media.Image
import android.media.Rating

data class Food(

    val txtTitle: String,
    val txtPrice: String,
    val txtDistance: String,
    val txtLocation: String,
    val urlImage: String,
    val numOfRating: Int,
    val rating: Float

)