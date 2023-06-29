package ru.pervukhin.gitwatcher.data.internet

import com.google.gson.annotations.SerializedName
import ru.pervukhin.gitwatcher.domain.Profile
import java.util.*

class RepositoryData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: ProfileData,
    @SerializedName("created_at")
    val dataCreated: Date,
    @SerializedName("pushed_at")
    val dateLastPushed: Date)