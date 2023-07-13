package ru.pervukhin.gitwatcher.domain

import java.util.*

class Repository(val id: Int,val name: String, val description: String?, val owner: Profile, val dateCreated: Date, val dateLastPushed: Date) {
    override fun equals(other: Any?): Boolean {
        return other is Repository && other.id == id && other.name == name && other.description == description && other.owner == owner && other.dateCreated == dateCreated && other.dateLastPushed == dateLastPushed
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