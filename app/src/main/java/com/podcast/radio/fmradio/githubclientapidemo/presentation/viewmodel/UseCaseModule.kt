package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository
import com.podcast.radio.fmradio.githubclientapidemo.domain.usecase.GetGithubRepoUseCase
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
    ):GetGithubRepoUseCase{
        return GetGithubRepoUseCase(githubRepoRepository)
    }
}