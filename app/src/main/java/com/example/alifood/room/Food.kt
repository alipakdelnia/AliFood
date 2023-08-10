package com.example.alifood.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_food",)
data class Food(

    @PrimaryKey(autoGenerate = true)
    val id : Int? = null ,

    val txtTitle: String,
    val txtPrice: String,
    val txtDistance: String,
    val txtLocation: String,

    @ColumnInfo(name = "url")
    val urlImage: String,
    val numOfRating: Int,
    val rating: Float

)