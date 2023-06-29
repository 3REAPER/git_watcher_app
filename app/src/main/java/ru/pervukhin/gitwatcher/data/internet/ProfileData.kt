package ru.pervukhin.gitwatcher.data.internet

import com.google.gson.annotations.SerializedName
import ru.pervukhin.gitwatcher.domain.Profile

class ProfileData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profileImage: String
){
    override fun equals(other: Any?): Boolean {
        return other is ProfileData && other.id == id && other.name == name && other.profileImage == profileImage
    }

    override fun hashCode(): Int {
        var result = id
        result += 31 * name.hashCode()
        result += 31 * profileImage.hashCode()
        return result
    }
}