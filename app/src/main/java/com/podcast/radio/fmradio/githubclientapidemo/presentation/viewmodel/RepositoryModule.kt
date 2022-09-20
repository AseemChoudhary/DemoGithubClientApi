package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import android.content.Context
import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoRemoteDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.GithubRepoRepositoryImpl
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(
        gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource,
        gitHubRepoLocalDataSource: GitHubRepoLocalDataSource,
        @ApplicationContext applicationContext: Context
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(
            gitHubRepoRemoteDataSource,
            gitHubRepoLocalDataSource,
            applicationContext
        )
    }

}