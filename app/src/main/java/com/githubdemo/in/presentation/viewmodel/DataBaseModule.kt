package com.githubdemo.`in`.presentation.viewmodel

import android.app.Application
import androidx.room.Room
import com.githubdemo.`in`.data.api.db.GitHubRepoListDao
import com.githubdemo.`in`.data.api.db.GithubRepoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideGithubRepoDatabase(app: Application): GithubRepoDb {
        return Room.databaseBuilder(app, GithubRepoDb::class.java, "gitRepo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubRepoDao(articleDatabase: GithubRepoDb): GitHubRepoListDao {
        return articleDatabase.getGitRepoDAO()
    }


}