package com.githubdemo.`in`.repository.datasourceImpl

import com.githubdemo.`in`.data.api.db.GitHubRepoListDao
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.githubdemo.`in`.repository.datasource.GitHubRepoLocalDataSource

class GitHubRepoLocalDataSourceImpl(private val gitHubRepoListDao: GitHubRepoListDao)
    : GitHubRepoLocalDataSource {
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