package com.podcast.radio.fmradio.githubclientapidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.podcast.radio.fmradio.githubclientapidemo.R
import com.podcast.radio.fmradio.githubclientapidemo.databinding.ActivityMainBinding
import com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel.GitHubViewModelFactory
import com.podcast.radio.fmradio.githubclientapidemo.presentation.viewmodel.GithubRepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var factory: GitHubViewModelFactory

    lateinit var viewModel: GithubRepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        viewModel = ViewModelProvider(this,factory)
            .get(GithubRepoViewModel::class.java)
    }
}