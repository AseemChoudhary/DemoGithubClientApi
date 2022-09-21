package com.githubdemo.`in`.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.githubdemo.`in`.domain.usecase.GetGithubRepoUseCase

class GitHubViewModelFactory(
    private val app: Application,
    private val getGithubRepoUseCase: GetGithubRepoUseCase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(
            app,
            getGithubRepoUseCase
        ) as T
    }
}