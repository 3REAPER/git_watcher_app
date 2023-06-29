package ru.pervukhin.gitwatcher.data.internet

import ru.pervukhin.gitwatcher.domain.Profile
import ru.pervukhin.gitwatcher.domain.Repository

class DataMapper {
    companion object{

        private fun profileDataToDomain(profileData: ProfileData): Profile{
            return Profile(
                profileData.id,
                profileData.name,
                profileData.profileImage
            )
        }

        fun profileDataListToDomain(profileDataList: List<ProfileData>): List<Profile> {
            var result: List<Profile> = listOf()
            for (profileData in profileDataList){
                result = result.plus(profileDataToDomain(profileData))
            }
            return result
        }

        private fun repositoryDataToDomain(repositoryData: RepositoryData): Repository{
            return Repository(
                repositoryData.id,
                repositoryData.name,
                repositoryData.description,
                profileDataToDomain(repositoryData.owner),
                repositoryData.dataCreated,
                repositoryData.dateLastPushed
            )
        }

        fun repositoryDataListToDomain(repositoryDataList: List<RepositoryData>): List<Repository> {
            var result: List<Repository> = listOf()
            for (repositoryData in repositoryDataList){
                result = result.plus(repositoryDataToDomain(repositoryData))
            }
            return result
        }

    }
}