package com.example.profileapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    var id: String,
    var pd: String,
    val age: String,
    val mbti: String,
    val gender: String
) : Parcelable
