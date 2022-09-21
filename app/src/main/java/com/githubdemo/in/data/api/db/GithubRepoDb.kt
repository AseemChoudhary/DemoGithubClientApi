package com.githubdemo.`in`.data.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem

@Database(
    entities = [GitRepoListPojoItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract  class GithubRepoDb : RoomDatabase(){
    abstract fun getGitRepoDAO(): GitHubRepoListDao
}