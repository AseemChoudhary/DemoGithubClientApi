package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository
import com.podcast.radio.fmradio.githubclientapidemo.repository.GitHubRepoRemoteDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.GithubRepoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(
        gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(
            gitHubRepoRemoteDataSource
        )
    }

}