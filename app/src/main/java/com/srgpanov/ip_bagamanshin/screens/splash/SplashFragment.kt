package com.srgpanov.ip_bagamanshin.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.srgpanov.ip_bagamanshin.App
import com.srgpanov.ip_bagamanshin.R
import com.srgpanov.ip_bagamanshin.databinding.FragmentSplashBinding
import com.srgpanov.ip_bagamanshin.screens.authorization.ConnectionDialog
import com.srgpanov.ip_bagamanshin.screens.web_screen.WebFragment.Companion.KEY_URL
import com.srgpanov.simpleweather.di.ViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding!!
    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private val navController by lazy { findNavController() }
    private var showTime = 0L
    private var launchNextFragmentJob: Job? = null

    companion object {
        private const val SPLASH_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.injectSplashFragment(this)
        viewModel = ViewModelProvider(this, factory)[SplashViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        showTime = System.currentTimeMillis()
    }

    private fun observeViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { data ->
            launchNextFragmentJob = lifecycleScope.launch {
                setupDelay()
                if (data.url == null) {
                    navController.navigate(R.id.action_splashFragment_to_authFragment)
                } else {
                    val bundle = bundleOf(KEY_URL to data.url)
                    navController.navigate(R.id.action_splashFragment_to_webFragment, bundle)
                }
            }
        }
        viewModel.showConnectionDialogEvent.observe(viewLifecycleOwner) {
            showConnectionDialog()
        }
    }

    private suspend fun setupDelay() {
        if (System.currentTimeMillis() - showTime < SPLASH_DELAY) {
            val remainingDelay = SPLASH_DELAY - (System.currentTimeMillis() - showTime)
            delay(remainingDelay)
        }
    }

    private fun setupListeners() {
        binding.tvSplash.setOnClickListener {
            navController.navigate(R.id.action_splashFragment_to_authFragment)
        }
    }

    private fun showConnectionDialog() {
        launchNextFragmentJob?.cancel()
        ConnectionDialog().show(childFragmentManager, ConnectionDialog.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
