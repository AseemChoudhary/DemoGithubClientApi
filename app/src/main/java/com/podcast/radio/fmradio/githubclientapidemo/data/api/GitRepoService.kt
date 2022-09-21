package com.podcast.radio.fmradio.githubclientapidemo.data.api


import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


 interface GitRepoService {
    @GET("/users/{username}/repos")
    suspend fun getGitRepo(@Path("username")user_name:String): Response<List<GitRepoListPojoItem>>
}
