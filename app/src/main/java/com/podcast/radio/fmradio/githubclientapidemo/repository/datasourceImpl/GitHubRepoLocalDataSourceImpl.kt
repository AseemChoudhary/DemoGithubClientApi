package com.podcast.radio.fmradio.githubclientapidemo.repository.datasourceImpl

import com.podcast.radio.fmradio.githubclientapidemo.data.api.db.GitHubRepoListDao
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoLocalDataSource
import kotlinx.coroutines.flow.Flow

class GitHubRepoLocalDataSourceImpl(private val gitHubRepoListDao: GitHubRepoListDao)
    : GitHubRepoLocalDataSource{
    override suspend fun saveGitHubRepoToDB(gitRepoListPojoItem: GitRepoListPojoItem) {
        gitHubRepoListDao.insert(gitRepoListPojoItem)
    }

    override suspend fun saveGitHubRepoToDBList(gitRepoListPojoItems: List<GitRepoListPojoItem>?) {
        gitHubRepoListDao.insertAll(gitRepoListPojoItems)
    }

    override fun getSavedGitHubRepoItem(): List<GitRepoListPojoItem> {
        return gitHubRepoListDao.getAllGithubItems()
    }

    override suspend fun deleteGitHubRepoFromDB(gitRepoListPojoItem: GitRepoListPojoItem) {
        gitHubRepoListDao.deleteGithubRepoList(gitRepoListPojoItem)
    }

    override suspend fun deleteAllData() {
        gitHubRepoListDao.deleteAllGithubRepoList()
    }
}