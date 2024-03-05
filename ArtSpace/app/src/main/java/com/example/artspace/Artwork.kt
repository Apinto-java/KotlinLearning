package com.example.artspace

import androidx.annotation.DrawableRes

public class Artwork(
    val title : String,
    val artist : String,
    val year : Int,
    @DrawableRes val image : Int
) {

}