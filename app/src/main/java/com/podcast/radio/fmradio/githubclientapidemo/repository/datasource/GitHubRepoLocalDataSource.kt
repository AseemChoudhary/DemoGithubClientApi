package com.podcast.radio.fmradio.githubclientapidemo.repository.datasource

import androidx.lifecycle.LiveData
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import kotlinx.coroutines.flow.Flow

interface GitHubRepoLocalDataSource {
    suspend fun saveGitHubRepoToDB(gitRepoListPojoItem: GitRepoListPojoItem)
    fun getSavedGitHubRepoItem(): List<GitRepoListPojoItem>
    suspend fun deleteGitHubRepoFromDB(gitRepoListPojoItem: GitRepoListPojoItem)

    suspend fun saveGitHubRepoToDBList(gitRepoListPojoItem: List<GitRepoListPojoItem>?)
}