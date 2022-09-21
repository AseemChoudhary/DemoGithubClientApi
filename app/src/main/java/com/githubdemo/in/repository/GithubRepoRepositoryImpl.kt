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
    private suspend fun getGitHubRepoFromApi(user_name:String): Resource<List<GitRepoListPojoItem>> {
        val response = gitHubRepoRemoteDataSource.getGitHubRepoList(user_name)
        sharedPreferences.edit().putLong(CACHE_TIME,System.currentTimeMillis()).apply()
        gitHubRepoLocalDataSource.saveGitHubRepoToDBList(response.body())
        return if(response.isSuccessful)
            listToResource(response.body()!!,"")
        else{
            listToResource(ArrayList<GitRepoListPojoItem>(),response.errorBody().toString())
        }

    }
    private suspend fun getGitHubRepoFromDataBase(user_name:String): Resource<List<GitRepoListPojoItem>> {
        var githubRepoItemList:List<GitRepoListPojoItem> = ArrayList<GitRepoListPojoItem>()
        try {
            if (sharedPreferences.contains(CACHE_TIME)) {
                val diff: Long =
                    (sharedPreferences.getLong(CACHE_TIME, 0) - System.currentTimeMillis())
                val seconds = diff / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                githubRepoItemList = gitHubRepoLocalDataSource.getSavedGitHubRepoItem()
                return if (hours < 2) {
                    if (githubRepoItemList.isNotEmpty()) {
                        Log.e("From DB","db")
                        listToResource(gitHubRepoLocalDataSource.getSavedGitHubRepoItem(),"")
                    } else {
                        Log.e("From internet","db")
                        gitHubRepoLocalDataSource.deleteAllData()
                        getGitHubRepoFromApi(user_name)
                    }
                } else {
                    getGitHubRepoFromApi(user_name)
                }
            }
            else {
                return getGitHubRepoFromApi(user_name)
            }
        }catch (e:Exception){
            Log.e("From internet","db")
            return getGitHubRepoFromApi(user_name)
        }
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