package com.githubdemo.`in`.presentation.viewmodel

import com.githubdemo.`in`.data.api.GitRepoService
import com.githubdemo.`in`.repository.datasource.GitHubRepoRemoteDataSource
import com.githubdemo.`in`.repository.datasourceImpl.GithubRepoRemoteDataSourceImpl
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