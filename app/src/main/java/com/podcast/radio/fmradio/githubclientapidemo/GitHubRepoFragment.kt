package com.podcast.radio.fmradio.githubclientapidemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.GITHUB_ITEM_BUNDLE_STRING
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_NO_INTERNET
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_LOADING
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.RESPONSE_SUCESS
import com.podcast.radio.fmradio.githubclientapidemo.databinding.GitHubRepoFragmentBinding
import com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel.GithubRepoViewModel
import com.podcast.radio.fmradio.githubclientapidemo.presentation.adapter.GitHubRepoListAdapter
import javax.inject.Inject

class GitHubRepoFragment : Fragment() {
    @Inject
    lateinit var factory: GithubRepoViewModel
    private lateinit var gitHubRepoListAdapter: GitHubRepoListAdapter
    private lateinit var fragmentGithubRepoBinding: GitHubRepoFragmentBinding
    private var isLoading = false
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
        fragmentGithubRepoBinding = GitHubRepoFragmentBinding.bind(view)
        gitHubRepoListAdapter= GitHubRepoListAdapter()
        initRecyclerView()
        gitHubRepoListAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(GITHUB_ITEM_BUNDLE_STRING,it)
            }
            findNavController().navigate(
                R.id.action_repodetailfrag,
                bundle
            )
        }
        viewModel.getGithubRepo("Octokit")
        viewModel.gitHubRepoList.observe(viewLifecycleOwner) { response ->
            when {
                response.sucess === RESPONSE_SUCESS -> {
                    hideProgressBar()
                    response.let {
                        gitHubRepoListAdapter.differ.submitList(it.githubRepo)
                    }
                }
                response.error == RESPONSE_LOADING -> {
                    showProgressBar()
                }
                response.error == RESPONSE_NO_INTERNET->{
                    hideProgressBar()
                    showNoInternetDialog("No Internet","Please check you're internet connection")
                }
                else -> {
                    hideProgressBar()
                    Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
                    showNoInternetDialog("Error","Server Error,Please Retry")
                }
            }
        }
    }




    private fun initRecyclerView() {
        fragmentGithubRepoBinding.rvGitRepo.apply {
            adapter = gitHubRepoListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun showProgressBar(){
        isLoading = true
        fragmentGithubRepoBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentGithubRepoBinding.progressBar.visibility = View.INVISIBLE
    }
    private fun showNoInternetDialog(strTitle:String, strMessage:String){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(strTitle)
        builder.setMessage(strMessage)
        builder.setPositiveButton("Retry") { dialog, which ->
            viewModel.getGithubRepo("Octokit")
        }

        builder.show()
    }
}