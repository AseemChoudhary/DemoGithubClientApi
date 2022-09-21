package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_ERROR
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_NO_INTERNET
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_LOADING
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_SUCESS
import com.podcast.radio.fmradio.githubclientapidemo.domain.usecase.GetGithubRepoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubRepoViewModel(private val app: Application,
                          private val getGithubRepoUseCase: GetGithubRepoUseCase):AndroidViewModel(app) {
    val gitHubRepoList: MutableLiveData<GitRepoListPojo> = MutableLiveData()
    fun getGithubRepo(user_name:String) = viewModelScope.launch(Dispatchers.IO) {
        var gitHubRepoData = GitRepoListPojo(null,"", RESPONSE_LOADING)
        gitHubRepoList.postValue(gitHubRepoData)
        try{
            val apiResult = getGithubRepoUseCase.execute(user_name)
            if(apiResult?.data?.size!! > 0) {
                gitHubRepoData = GitRepoListPojo(apiResult.data,  RESPONSE_SUCESS , "")
                gitHubRepoList.postValue(gitHubRepoData)
            }
            else{
                if(!isNetworkAvailable(app)){
                    gitHubRepoData = GitRepoListPojo(null,"", RESPONSE_NO_INTERNET)
                    gitHubRepoList.postValue(gitHubRepoData)
               //   Toast.makeText(app,"No Internet",Toast.LENGTH_SHORT).show()
                }
                else{
                    gitHubRepoData = GitRepoListPojo(null,"", RESPONSE_ERROR)
                    gitHubRepoList.postValue(gitHubRepoData)
                }
            }
        }catch (e:Exception){
            if(!isNetworkAvailable(app)){
                gitHubRepoData = GitRepoListPojo(null,"", RESPONSE_NO_INTERNET)
                gitHubRepoList.postValue(gitHubRepoData)
            }else {
                gitHubRepoData = GitRepoListPojo(null, "", RESPONSE_ERROR)
                gitHubRepoList.postValue(gitHubRepoData)
            }
            }
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
