package ru.pervukhin.gitwatcher.data.internet

import ru.pervukhin.gitwatcher.App
import ru.pervukhin.gitwatcher.domain.GitRepository
import ru.pervukhin.gitwatcher.domain.Profile
import ru.pervukhin.gitwatcher.domain.Repository
import javax.inject.Inject

class GitRepositoryImpl: GitRepository {
    @Inject
    lateinit var gitApi: GitApi

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getWatchers(repositoryName: String, profileName: String): List<Profile>? {
        gitApi.getWatches(repositoryName, profileName).let {
            val body = it.body()
            if (it.isSuccessful && body != null){
                return DataMapper.profileDataListToDomain(body)
            }
            return null

        }
    }

    override suspend fun getRepositoryByOwner(ownerName: String): List<Repository>? {
        gitApi.getRepositoryByOwner(ownerName).let {
            val body = it.body()
            if (it.isSuccessful && body != null){
                return DataMapper.repositoryDataListToDomain(body)
            }
            return null
        }
    }
}