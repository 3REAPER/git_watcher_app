package ru.pervukhin.gitwatcher.domain

interface GitRepository {

    suspend fun getWatchers(repositoryName : String, profileName: String): List<Profile>?

    suspend fun getRepositoryByOwner(ownerName : String): List<Repository>?
}