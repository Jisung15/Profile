package com.example.profileapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserClass(val name: String, val id: String, val pd:String, val age: String, val mbti: String, val gender: String) : Parcelable
