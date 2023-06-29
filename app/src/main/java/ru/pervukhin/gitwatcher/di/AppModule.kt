package ru.pervukhin.gitwatcher.di

import dagger.Module
import dagger.Provides
import ru.pervukhin.gitwatcher.data.internet.GitRepositoryImpl
import ru.pervukhin.gitwatcher.domain.GitRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGitRepository(): GitRepository{
        return GitRepositoryImpl()
    }
}