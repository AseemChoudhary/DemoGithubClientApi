package com.podcast.radio.fmradio.githubclientapidemo.data.api.db

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem

@Database(
    entities = [GitRepoListPojoItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract  class GithubRepoDb : RoomDatabase(){
    abstract fun getGitRepoDAO():GitHubRepoListDao
}