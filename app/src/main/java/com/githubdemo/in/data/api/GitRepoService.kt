package com.githubdemo.`in`.data.api


import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GitRepoService {
    @GET("/users/{username}/repos")
    suspend fun getGitRepo(@Path("username")user_name:String): Response<List<GitRepoListPojoItem>>
}
