package ru.pervukhin.gitwatcher.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.gitwatcher.data.internet.GitApi
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit): GitApi {
        return retrofit.create(GitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}