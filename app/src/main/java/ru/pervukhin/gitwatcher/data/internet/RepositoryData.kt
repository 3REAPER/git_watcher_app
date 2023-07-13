package ru.pervukhin.gitwatcher.data.internet

import com.google.gson.annotations.SerializedName
import ru.pervukhin.gitwatcher.domain.Profile
import ru.pervukhin.gitwatcher.domain.Repository
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
    val dateCreated: Date,
    @SerializedName("pushed_at")
    val dateLastPushed: Date?){

    override fun equals(other: Any?): Boolean {
        return other is RepositoryData && other.id == id && other.name == name && other.description == description && other.owner == owner && other.dateCreated.time == dateCreated.time && other.dateLastPushed?.time == dateLastPushed?.time
    }

    override fun hashCode(): Int {
        var result = id
        result += 31 * name.hashCode()
        result += 31 * description.hashCode()
        result += 31 * owner.hashCode()
        result += 31 * dateCreated.hashCode()
        result += 31 * dateLastPushed.hashCode()
        return result
    }
}