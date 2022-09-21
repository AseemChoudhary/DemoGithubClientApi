package com.githubdemo.`in`.repository.datasourceImpl

import com.githubdemo.`in`.data.api.GitRepoService
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.githubdemo.`in`.repository.datasource.GitHubRepoRemoteDataSource
import retrofit2.Response

class GithubRepoRemoteDataSourceImpl(private val gitRepoService: GitRepoService):
    GitHubRepoRemoteDataSource {
    override suspend fun getGitHubRepoList(user_name:String): Response<List<GitRepoListPojoItem>> {
        return gitRepoService.getGitRepo(user_name)
    }
}