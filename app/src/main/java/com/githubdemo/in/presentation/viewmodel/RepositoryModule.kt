package com.githubdemo.`in`.presentation.viewmodel

import android.content.Context
import com.githubdemo.`in`.domain.repository.GithubRepoRepository
import com.githubdemo.`in`.repository.datasource.GitHubRepoRemoteDataSource
import com.githubdemo.`in`.repository.GithubRepoRepositoryImpl
import com.githubdemo.`in`.repository.datasource.GitHubRepoLocalDataSource
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