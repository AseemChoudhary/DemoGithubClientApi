package com.githubdemo.`in`.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.podcast.radio.fmradio.githubclientapidemo.R
import com.githubdemo.`in`.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.databinding.ItemGithubRepoBinding

class GitHubRepoListAdapter : RecyclerView.Adapter<GitHubRepoListAdapter.GitHubRepoViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<GitRepoListPojoItem>(){
        override fun areItemsTheSame(oldItem: GitRepoListPojoItem, newItem: GitRepoListPojoItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: GitRepoListPojoItem, newItem: GitRepoListPojoItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder {
        val binding = ItemGithubRepoBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return GitHubRepoViewHolder(binding)
    }



    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class GitHubRepoViewHolder(
        val binding:ItemGithubRepoBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(gitRepoListPojoItem: GitRepoListPojoItem){
            Log.i("MYTAG","came here ${gitRepoListPojoItem.description}")
            binding.tvName.text = gitRepoListPojoItem.fullName
            binding.tvDescription.text = gitRepoListPojoItem.description
            Glide.with(binding.ivArticleImage.context).
            load(R.mipmap.ic_launcher).
            into(binding.ivArticleImage)
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(gitRepoListPojoItem)
                }
            }
        }
    }

    private var onItemClickListener :((GitRepoListPojoItem)->Unit)?=null

    fun setOnItemClickListener(listener : (GitRepoListPojoItem)->Unit){
        onItemClickListener = listener
    }


}
