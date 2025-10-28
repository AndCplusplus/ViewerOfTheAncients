package com.example.vieweroftheancients

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("localized_name")
    val localizedName: String,
    @SerializedName("primary_attr")
    val primaryAttr: String,
    val roles: List<String>
)
