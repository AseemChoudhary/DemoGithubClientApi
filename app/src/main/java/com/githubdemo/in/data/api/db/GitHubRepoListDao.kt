package com.githubdemo.`in`.data.api.db

import androidx.room.*
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem

@Dao
interface GitHubRepoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitRepoListPojoItem: GitRepoListPojoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gitRepoListPojoItem: List<GitRepoListPojoItem>? )

    @Query("SELECT * FROM github_item")
    fun getAllGithubItems(): List<GitRepoListPojoItem>

    @Delete
    suspend fun deleteGithubRepoList(gitRepoListPojoItem: GitRepoListPojoItem)

    @Query("DELETE FROM github_item")
    suspend fun deleteAllGithubRepoList()
}