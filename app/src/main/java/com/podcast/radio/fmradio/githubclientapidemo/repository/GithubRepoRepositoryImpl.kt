package com.podcast.radio.fmradio.githubclientapidemo.repository

import android.util.Log
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource
import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository
import retrofit2.Response

class GithubRepoRepositoryImpl(private val gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource):GithubRepoRepository {
    override suspend fun getGithubRepoItem(user_name:String):  Resource<List<GitRepoListPojoItem>> {
      return getGitHubRepoFromApi(user_name)
    }
    suspend fun getGitHubRepoFromApi(user_name:String):Resource<List<GitRepoListPojoItem>>{
        lateinit var githubRepoList:Response<List<GitRepoListPojoItem>>
        try {
            var response = gitHubRepoRemoteDataSource.getGitHubRepoList(user_name)
            var body = response.body()
            if (body != null){
                githubRepoList = response
                return responseToResource( githubRepoList)
            }
        }
        catch (excetion:Exception){
             Log.e("fa",excetion.toString())
        }

        return responseToResource(githubRepoList)
    }
    private fun responseToResource(response: Response<List<GitRepoListPojoItem>>):Resource<List<GitRepoListPojoItem>>{
        if(response.isSuccessful){
            response.body()?.let {result->

                return Resource.SuccessList(result)
            }
        }
        return Resource.Error(response.message())
    }
}