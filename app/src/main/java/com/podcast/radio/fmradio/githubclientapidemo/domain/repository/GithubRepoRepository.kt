package com.podcast.radio.fmradio.githubclientapidemo.domain.repository

import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource

interface GithubRepoRepository {
    suspend fun getGithubRepoItem(user_name:String): Resource<List<GitRepoListPojoItem>>
}