package com.githubdemo.`in`.presentation.viewmodel

import android.app.Application
import com.githubdemo.`in`.domain.usecase.GetGithubRepoUseCase
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
    ): GitHubViewModelFactory {
        return GitHubViewModelFactory(
            application,
            getGithubRepoUseCase
        )
    }
}