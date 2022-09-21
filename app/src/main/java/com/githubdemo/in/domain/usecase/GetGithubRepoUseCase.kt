package com.githubdemo.`in`.domain.usecase

import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.githubdemo.`in`.data.util.Resource
import com.githubdemo.`in`.domain.repository.GithubRepoRepository

class GetGithubRepoUseCase(private val githubRepoRepository: GithubRepoRepository) {
    suspend fun execute(user_name:String): Resource<List<GitRepoListPojoItem>> {
        return githubRepoRepository.getGithubRepoItem(user_name)
    }
}