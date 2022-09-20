package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import com.podcast.radio.fmradio.githubclientapidemo.data.api.GitRepoService
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoRemoteDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasourceImpl.GithubRepoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideGitHubRepoRemoteDataSource(
        gitRepoService: GitRepoService
    ): GitHubRepoRemoteDataSource {
        return GithubRepoRemoteDataSourceImpl(gitRepoService)
    }

}