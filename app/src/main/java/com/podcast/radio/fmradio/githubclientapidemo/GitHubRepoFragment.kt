package com.podcast.radio.fmradio.githubclientapidemo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Resource
import com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel.GithubRepoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepoFragment : Fragment() {
    @Inject
    lateinit var factory: GithubRepoViewModel
    companion object {
        fun newInstance() = GitHubRepoFragment()
    }

    private lateinit var viewModel: GithubRepoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.git_hub_repo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        viewModel.getGithubRepo("Octokit")
        viewModel.githubrepoList.observe(viewLifecycleOwner,{response->
           if(response.sucess === "Sucess") {
               response.let {
                   Toast.makeText(activity, "came here ${it.githubRepo?.size}", Toast.LENGTH_LONG)
                       .show()
                   Log.i("MYTAG", "came here ${it?.githubRepo?.size}")
               }
           }
            else {
                    Toast.makeText(activity,"error",Toast.LENGTH_LONG).show()
                }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(GithubRepoViewModel::class.java)
//        viewModel.getGithubRepo("Octokit")

    }

}