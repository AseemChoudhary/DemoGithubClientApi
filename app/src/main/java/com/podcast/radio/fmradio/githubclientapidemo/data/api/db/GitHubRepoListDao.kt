package com.podcast.radio.fmradio.githubclientapidemo.data.api.db

import androidx.room.*
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GitHubRepoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitRepoListPojoItem: GitRepoListPojoItem )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gitRepoListPojoItem: List<GitRepoListPojoItem>? )

    @Query("SELECT * FROM github_item")
    fun getAllGithubItems(): List<GitRepoListPojoItem>

    @Delete
    suspend fun deleteGithubRepoList(gitRepoListPojoItem: GitRepoListPojoItem)
}