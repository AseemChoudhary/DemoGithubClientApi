package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import android.app.Application
import com.podcast.radio.fmradio.githubclientapidemo.domain.usecase.GetGithubRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideGitHubViewModelFactory(
        application: Application,
        getGithubRepoUseCase: GetGithubRepoUseCase
    ):GitHubViewModelFactory{
        return GitHubViewModelFactory(
            application,
            getGithubRepoUseCase
        )
    }
}