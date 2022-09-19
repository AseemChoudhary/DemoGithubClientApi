package com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojo
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource
import com.podcast.radio.fmradio.githubclientapidemo.domain.usecase.GetGithubRepoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubRepoViewModel(private val app: Application,
                          private val getGithubRepoUseCase: GetGithubRepoUseCase):AndroidViewModel(app) {
    val githubrepoList: MutableLiveData<GitRepoListPojo> = MutableLiveData()
    fun getGithubRepo(user_name:String) = viewModelScope.launch(Dispatchers.IO) {
        var githubrepoLista:GitRepoListPojo = GitRepoListPojo(null,"","error")
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getGithubRepoUseCase.execute(user_name)
               githubrepoLista = GitRepoListPojo(apiResult.data,"Sucess","error")
                githubrepoList.postValue(githubrepoLista)
            }else{
                githubrepoList.postValue(githubrepoLista)
            }

        }catch (e:Exception){
            githubrepoList.postValue(githubrepoLista)
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
}