package ru.pervukhin.gitwatcher.data.internet

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi {

    @GET("repos/{owner}/{repo}/subscribers")
    suspend fun getWatches(@Path("repo") repository: String, @Path("owner")profile: String): Response<List<ProfileData>>

    @GET("users/{user}/repos")
    suspend fun getRepositoryByOwner(@Path("user") name: String): Response<List<RepositoryData>>
}