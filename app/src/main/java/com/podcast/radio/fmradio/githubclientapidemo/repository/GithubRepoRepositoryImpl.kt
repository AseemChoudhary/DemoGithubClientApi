package com.podcast.radio.fmradio.githubclientapidemo.repository

import android.content.Context
import android.util.Log
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.CACHE_TIME
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.SHAREDPREF_FILENAME
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource
import com.podcast.radio.fmradio.githubclientapidemo.domain.repository.GithubRepoRepository
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoLocalDataSource
import com.podcast.radio.fmradio.githubclientapidemo.repository.datasource.GitHubRepoRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response

class GithubRepoRepositoryImpl(private val gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource,
                               private val gitHubRepoLocalDataSource: GitHubRepoLocalDataSource,
                               @ApplicationContext private val appContext: Context )
    :GithubRepoRepository {
    val sharedPreferences =
        appContext.getSharedPreferences(SHAREDPREF_FILENAME, Context.MODE_PRIVATE);
    override suspend fun getGithubRepoItem(user_name:String):  Resource<List<GitRepoListPojoItem>> {
      return getGitHubRepoFromDataBase(user_name)
    }
    suspend fun getGitHubRepoFromApi(user_name:String):Resource<List<GitRepoListPojoItem>>{
        lateinit var githubRepoList:Response<List<GitRepoListPojoItem>>
        try {
            var response = gitHubRepoRemoteDataSource.getGitHubRepoList(user_name)
            var body = response.body()
            if (body != null){
                sharedPreferences.edit().putLong(CACHE_TIME,System.currentTimeMillis()).commit()
                gitHubRepoLocalDataSource.saveGitHubRepoToDBList(response.body())
                githubRepoList = response
                return responseToResource( githubRepoList)
            }
        }
        catch (excetion:Exception){
            return responseToResource(githubRepoList)
        }

        return responseToResource(githubRepoList)
    }
    suspend fun getGitHubRepoFromDataBase(user_name:String):Resource<List<GitRepoListPojoItem>>{
        var githubRepoItemList:List<GitRepoListPojoItem> = ArrayList<GitRepoListPojoItem>()
        try {

            if (sharedPreferences.contains(CACHE_TIME)) {
                val diff: Long =
                    (sharedPreferences.getLong(CACHE_TIME, 0) - System.currentTimeMillis())
                val seconds = diff / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                githubRepoItemList = gitHubRepoLocalDataSource.getSavedGitHubRepoItem()
                if (hours < 2) {
                    if (githubRepoItemList.size > 0) {
                        Log.e("FRom DB","db")
                        return listToResource(gitHubRepoLocalDataSource.getSavedGitHubRepoItem())
                    } else {
                        Log.e("FRom internet","db")
                        getGitHubRepoFromApi(user_name)
                    }
                }

            }
            else {
                Log.e("FRom internet","db")
                getGitHubRepoFromApi(user_name)
            }
        }catch (e:Exception){
            Log.e("FRom internet","db")
            getGitHubRepoFromApi(user_name)
        }
       return listToResource(githubRepoItemList)
    }
    private fun responseToResource(response: Response<List<GitRepoListPojoItem>>):Resource<List<GitRepoListPojoItem>>{
        if(response.isSuccessful){
            response.body()?.let {result->

                return Resource.SuccessList(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun listToResource(response: List<GitRepoListPojoItem>):Resource<List<GitRepoListPojoItem>>{
        if(response.isNotEmpty()){
            response.let { result->
                return Resource.SuccessList(result)
            }
        }
        return Resource.Error("Error")
    }
}