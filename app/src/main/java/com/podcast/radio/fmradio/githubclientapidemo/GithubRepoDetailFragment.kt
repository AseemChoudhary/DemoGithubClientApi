package com.podcast.radio.fmradio.githubclientapidemo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.podcast.radio.fmradio.githubclientapidemo.data.api.model.githubrepo.GitRepoListPojoItem
import com.podcast.radio.fmradio.githubclientapidemo.data.util.Constant.GITHUB_ITEM_BUNDLE_STRING
import com.podcast.radio.fmradio.githubclientapidemo.databinding.GitHubRepoFragmentBinding
import com.podcast.radio.fmradio.githubclientapidemo.databinding.GithubRepoDetailFragmentBinding
import com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel.GithubRepoDetailViewModel

class GithubRepoDetailFragment : Fragment() {
    private lateinit var fragmentBinding: GithubRepoDetailFragmentBinding
    companion object {
        fun newInstance() = GithubRepoDetailFragment()
    }

    private lateinit var viewModel: GithubRepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.github_repo_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding = GithubRepoDetailFragmentBinding.bind(view)
        val gitRepoListPojoItem:GitRepoListPojoItem = arguments?.getSerializable(GITHUB_ITEM_BUNDLE_STRING) as GitRepoListPojoItem
        fragmentBinding.txtDesc.text = gitRepoListPojoItem.description
        fragmentBinding.imgGit.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GithubRepoDetailViewModel::class.java)

    }

}