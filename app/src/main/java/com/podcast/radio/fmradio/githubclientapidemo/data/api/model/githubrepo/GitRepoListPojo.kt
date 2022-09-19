package com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo



data class GitRepoListPojo(var githubRepo: List<GitRepoListPojoItem>?,
                           var suces:String,var erro:String){
    var sucess:String=suces
    var error:String=erro
    var githubRepos:List<GitRepoListPojoItem> = this.githubRepo?: ArrayList<GitRepoListPojoItem>()
}