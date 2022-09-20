package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import com.podcast.radio.fmradio.githubclientapidemo.data.api.GitRepoService
import com.podcast.radio.fmradio.githubclientapidemo.data.api.db.GitHubRepoListDao
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoLocalDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoRemoteDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasourceImpl.GitHubRepoLocalDataSourceImpl
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasourceImpl.GithubRepoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


    @InstallIn(SingletonComponent::class)
    @Module
    class LocalDataModule {

        @Singleton
        @Provides
        fun provideGitHubRepoLocalDataSource(
            gitRepoService: GitHubRepoListDao
        ): GitHubRepoLocalDataSource {
            return GitHubRepoLocalDataSourceImpl(gitRepoService)
        }

    }
