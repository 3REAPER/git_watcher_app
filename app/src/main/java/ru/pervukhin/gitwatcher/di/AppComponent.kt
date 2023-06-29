package ru.pervukhin.gitwatcher.di

import dagger.Component
import ru.pervukhin.gitwatcher.MainActivity
import ru.pervukhin.gitwatcher.data.internet.GitApi
import ru.pervukhin.gitwatcher.data.internet.GitRepositoryImpl
import ru.pervukhin.gitwatcher.domain.GitRepository
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, AppModule::class])
@Singleton
interface AppComponent {

    fun inject(gitRepositoryImpl: GitRepositoryImpl)
    fun inject(mainActivity: MainActivity)

    fun gitApi(): GitApi

    fun gitRepository(): GitRepository
}