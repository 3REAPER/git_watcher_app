package ru.pervukhin.gitwatcher.data.internet

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {

    @GET("repos/{owner}/{repo}/subscribers")
    suspend fun getWatches(@Path("repo") repository: String, @Path("owner")profile: String): Response<List<ProfileData>>

    @GET("users/{owner}/repos")
    suspend fun getRepositoryByOwner(@Path("owner") ownerName: String): Response<List<RepositoryData>>
}