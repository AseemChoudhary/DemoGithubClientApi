package com.githubdemo.`in`.repository

import android.content.Context
import android.util.Log
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.githubdemo.`in`.data.util.Constant.CACHE_TIME
import com.githubdemo.`in`.data.util.Constant.RESPONSE_ERROR
import com.githubdemo.`in`.data.util.Constant.SHAREDPREF_FILENAME
import com.githubdemo.`in`.data.util.Resource
import com.githubdemo.`in`.domain.repository.GithubRepoRepository
import com.githubdemo.`in`.repository.datasource.GitHubRepoLocalDataSource
import com.githubdemo.`in`.repository.datasource.GitHubRepoRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response

class GithubRepoRepositoryImpl(private val gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource,
                               private val gitHubRepoLocalDataSource: GitHubRepoLocalDataSource,
                               @ApplicationContext private val appContext: Context )
    : GithubRepoRepository {
    val sharedPreferences =
        appContext.getSharedPreferences(SHAREDPREF_FILENAME, Context.MODE_PRIVATE);
    override suspend fun getGithubRepoItem(user_name:String): Resource<List<GitRepoListPojoItem>> {
      return getGitHubRepoFromDataBase(user_name)
    }
    suspend fun getGitHubRepoFromApi(user_name:String): Resource<List<GitRepoListPojoItem>> {
        lateinit var githubRepoList:Response<List<GitRepoListPojoItem>>
        try {
            val response = gitHubRepoRemoteDataSource.getGitHubRepoList(user_name)
            var body = response.body()
            if (body != null){
                sharedPreferences.edit().putLong(CACHE_TIME,System.currentTimeMillis()).apply()
                gitHubRepoLocalDataSource.saveGitHubRepoToDBList(response.body())
                githubRepoList = response
                return responseToResource(githubRepoList,"")
            }
        }
        catch (excetion:Exception){
            return responseToResource(githubRepoList, RESPONSE_ERROR)
        }

        return responseToResource(githubRepoList,RESPONSE_ERROR)
    }
    suspend fun getGitHubRepoFromDataBase(user_name:String): Resource<List<GitRepoListPojoItem>> {
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
                        return listToResource(gitHubRepoLocalDataSource.getSavedGitHubRepoItem(),"")
                    } else {
                        Log.e("From internet","db")
                        gitHubRepoLocalDataSource.deleteAllData()
                        getGitHubRepoFromApi(user_name)
                    }
                }

            }
            else {
                Log.e("From internet","db")
                getGitHubRepoFromApi(user_name)
            }
        }catch (e:Exception){
            Log.e("From internet","db")
            getGitHubRepoFromApi(user_name)
        }
       return listToResource(githubRepoItemList, RESPONSE_ERROR)
    }
    private fun responseToResource(response: Response<List<GitRepoListPojoItem>>, errorBody: String): Resource<List<GitRepoListPojoItem>> {
        if(response.isSuccessful){
            response.body()?.let {result->

                return Resource.SuccessList(result)
            }
        }
        return Resource.Error(errorBody)
    }

    private fun listToResource(response: List<GitRepoListPojoItem>, errorBody: String): Resource<List<GitRepoListPojoItem>> {
        if(response.isNotEmpty()){
            response.let { result->
                return Resource.SuccessList(result)
            }
        }
        return Resource.Error(errorBody)
    }
}