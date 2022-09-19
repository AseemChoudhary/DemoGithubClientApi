package com.podcast.radio.fmradio.githubclientapidemo.repository

import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import retrofit2.Response
import java.util.*

interface GitHubRepoRemoteDataSource {
    suspend fun getGitHubRepoList(user_name:String): Response<List<GitRepoListPojoItem>>
}