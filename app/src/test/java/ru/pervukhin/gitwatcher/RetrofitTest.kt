package ru.pervukhin.gitwatcher

import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.gitwatcher.data.internet.GitApi
import ru.pervukhin.gitwatcher.data.internet.ProfileData
import ru.pervukhin.gitwatcher.data.internet.RepositoryData
import java.util.*

class RetrofitTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var gitApi: GitApi
    private val repositoryJson = "[ {\n" +
            "        \"id\": 495075924,\n" +
            "        \"name\": \"3REAPER\",\n" +
            "        \"owner\": {\n" +
            "            \"login\": \"3REAPER\",\n" +
            "            \"id\": 105064922,\n" +
            "            \"avatar_url\": \"https://avatars.githubusercontent.com/u/105064922?v=4\"\n" +
            "        },\n" +
            "        \"description\": null,\n" +
            "        \"created_at\": \"2023-06-07T18:13:50Z\",\n" +
            "        \"pushed_at\": \"2023-06-17T18:10:02Z\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 650749025,\n" +
            "        \"name\": \"food-shop\",\n" +
            "        \"owner\": {\n" +
            "            \"login\": \"4REAPER\",\n" +
            "            \"id\": 105064922,\n" +
            "            \"avatar_url\": \"https://avatars.githubusercontent.com/u/105064922?v=4\"\n" +
            "        },\n" +
            "        \"description\": null,\n" +
            "        \"created_at\": \"2023-06-19T18:10:02Z\",\n" +
            "        \"pushed_at\": \"2023-06-21T18:10:02Z\"\n" +
            "    }]"
    private val profileJson = "[{\n" +
            "        \"login\": \"3REAPER\",\n" +
            "        \"id\": 105064922,\n" +
            "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/105064922?v=4\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"4REAPER\",\n" +
            "        \"id\": 105064922,\n" +
            "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/105064922?v=4\"\n" +
            "    }]"

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        gitApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").create()))
            .build().create(GitApi::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testGetRepository() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody(repositoryJson)
        mockWebServer.enqueue(mockResponse)

        val response = gitApi.getRepositoryByOwner("").body()
        mockWebServer.takeRequest()

        val repositoryData1 = RepositoryData(495075924,"3REAPER",null, ProfileData(105064922,"3REAPER", "https://avatars.githubusercontent.com/u/105064922?v=4"), Date(1686161630000), Date(1687025402000))
        val repositoryData2 = RepositoryData(650749025,"food-shop",null, ProfileData(105064922, "4REAPER", "https://avatars.githubusercontent.com/u/105064922?v=4"), Date(1687198202000), Date(1687371002000))

        Assert.assertEquals(repositoryData1, response?.get(0))
        Assert.assertEquals(repositoryData2, response?.get(1))
    }

    @Test
    fun testEmptyBody() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = gitApi.getRepositoryByOwner("").body()
        mockWebServer.takeRequest()

        val expected: List<RepositoryData> = listOf()
        Assert.assertEquals(expected, response)
    }

    @Test
    fun testGetWatchers() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody(profileJson)
        mockWebServer.enqueue(mockResponse)

        val response = gitApi.getWatches("","").body()
        mockWebServer.takeRequest()

        val profileData1 = ProfileData(105064922,"3REAPER", "https://avatars.githubusercontent.com/u/105064922?v=4")
        val profileData2 = ProfileData(105064922, "4REAPER", "https://avatars.githubusercontent.com/u/105064922?v=4")

        Assert.assertEquals(profileData1, response?.get(0))
        Assert.assertEquals(profileData2, response?.get(1))
    }

    @Test
    fun testGetWatchersEmpty() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        Assert.assertEquals(listOf<ProfileData>(), gitApi.getWatches("","").body())
    }
}