package com.podcast.radio.fmradio.githubclientapidemo.network


import com.podcast.radio.fmradio.githubclientapidemo.BuildConfig
import com.podcast.radio.fmradio.githubclientapidemo.data.api.GitRepoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BuildConfig.BASE_URL)
             .build()
    }

    @Singleton
    @Provides
    fun provideGithubAPIService(retrofit: Retrofit):GitRepoService{
        return retrofit.create(GitRepoService::class.java)
    }



}













