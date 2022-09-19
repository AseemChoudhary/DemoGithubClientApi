package com.podcast.radio.fmradio.githubclientapidemo.domain.usecase

import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource
import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository

class GetGithubRepoUseCase(private val githubRepoRepository: GithubRepoRepository) {
    suspend fun execute(user_name:String): Resource<List<GitRepoListPojoItem>>{
        return githubRepoRepository.getGithubRepoItem(user_name)
    }
}