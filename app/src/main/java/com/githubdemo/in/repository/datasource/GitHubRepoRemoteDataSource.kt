package com.githubdemo.`in`.repository.datasource

import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import retrofit2.Response

interface GitHubRepoRemoteDataSource {
    suspend fun getGitHubRepoList(user_name:String): Response<List<GitRepoListPojoItem>>
}