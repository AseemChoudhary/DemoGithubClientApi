package com.githubdemo.`in`.repository.datasource

import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem

interface GitHubRepoLocalDataSource {
    suspend fun saveGitHubRepoToDB(gitRepoListPojoItem: GitRepoListPojoItem)
    fun getSavedGitHubRepoItem(): List<GitRepoListPojoItem>
    suspend fun deleteGitHubRepoFromDB(gitRepoListPojoItem: GitRepoListPojoItem)
    suspend fun deleteAllData()
    suspend fun saveGitHubRepoToDBList(gitRepoListPojoItem: List<GitRepoListPojoItem>?)
}