package ru.pervukhin.gitwatcher

import org.junit.Test

import org.junit.Assert.*
import ru.pervukhin.gitwatcher.data.internet.DataMapper
import ru.pervukhin.gitwatcher.data.internet.ProfileData
import ru.pervukhin.gitwatcher.data.internet.RepositoryData
import ru.pervukhin.gitwatcher.domain.Profile
import ru.pervukhin.gitwatcher.domain.Repository
import java.util.*

class MapperTest {
    @Test
    fun testListRepositoryDataToDomain(){
        val repositoryData1 = RepositoryData(1,"2", "test1", ProfileData(3,"4","5"),Date(6), Date(7))
        val repositoryData2 = RepositoryData(8,"9", "test2", ProfileData(10,"11","12"),Date(13), Date(14))
        val expected: List<Repository> = listOf(Repository(1,"2", "test1", Profile(3,"4","5"),Date(6), Date(7)), Repository(8,"9", "test2", Profile(10,"11","12"),Date(13), Date(14)))
        assertEquals(expected, DataMapper.repositoryDataListToDomain(listOf(repositoryData1, repositoryData2)))
    }


    @Test
    fun testListDataToDomain(){
        val profileData1 = ProfileData(1,"2", "3")
        val profileData2 = ProfileData(4,"5", "6")
        val expected: List<Profile> = listOf(Profile(1,"2", "3"), Profile(4,"5", "6"))
        assertEquals(expected, DataMapper.profileDataListToDomain(listOf(profileData1, profileData2)))
    }
}