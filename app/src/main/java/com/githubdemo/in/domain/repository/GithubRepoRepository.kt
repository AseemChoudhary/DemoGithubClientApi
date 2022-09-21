package com.githubdemo.`in`.domain.repository

import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.githubdemo.`in`.data.util.Resource

interface GithubRepoRepository {
    suspend fun getGithubRepoItem(user_name:String): Resource<List<GitRepoListPojoItem>>
}