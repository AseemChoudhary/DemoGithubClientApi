package com.githubdemo.`in`.presentation.viewmodel

import com.githubdemo.`in`.domain.repository.GithubRepoRepository
import com.githubdemo.`in`.domain.usecase.GetGithubRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetGitRepoUseCase(
        githubRepoRepository: GithubRepoRepository
    ): GetGithubRepoUseCase {
        return GetGithubRepoUseCase(githubRepoRepository)
    }
}