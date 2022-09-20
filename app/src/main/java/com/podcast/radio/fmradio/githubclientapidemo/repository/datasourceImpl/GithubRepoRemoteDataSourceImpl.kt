package com.podcast.radio.fmradio.githubclientapidemo.repository.datasourceImpl

import com.podcast.radio.fmradio.githubclientapidemo.data.api.GitRepoService
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoRemoteDataSource
import retrofit2.Response

class GithubRepoRemoteDataSourceImpl(private val gitRepoService: GitRepoService):
    GitHubRepoRemoteDataSource {
    override suspend fun getGitHubRepoList(user_name:String): Response<List<GitRepoListPojoItem>> {
        return gitRepoService.getGitRepo(user_name)
    }
}