package ru.pervukhin.gitwatcher.domain

class Profile(val id: Int, val name: String, val profileImage: String){
    override fun equals(other: Any?): Boolean {
        return other is Profile && other.id == id && other.name == name && other.profileImage == profileImage
    }

    override fun hashCode(): Int {
        var result = id
        result += 31 * name.hashCode()
        result += 31 * profileImage.hashCode()
        return result
    }
}