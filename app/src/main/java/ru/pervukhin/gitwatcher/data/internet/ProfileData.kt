package ru.pervukhin.gitwatcher.data.internet

import com.google.gson.annotations.SerializedName

class ProfileData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profileImage: String
)