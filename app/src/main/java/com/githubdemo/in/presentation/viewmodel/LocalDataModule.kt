package com.githubdemo.`in`.presentation.viewmodel

import com.githubdemo.`in`.data.api.db.GitHubRepoListDao
import com.githubdemo.`in`.repository.datasource.GitHubRepoLocalDataSource
import com.githubdemo.`in`.repository.datasourceImpl.GitHubRepoLocalDataSourceImpl
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
