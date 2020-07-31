package com.srgpanov.ip_bagamanshin.screens.web_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.srgpanov.ip_bagamanshin.databinding.FragmentWebBinding


//не было опыта работы с веб вью, поэтому не весь требуемый функционал реализовал
class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding: FragmentWebBinding
        get() = _binding!!

    private lateinit var argUrl: String

    companion object {
        const val KEY_URL = "KEY_URL"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argUrl =
            arguments?.getString(KEY_URL) ?: throw IllegalArgumentException("Bundle has null key")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //загрузка файлов
        binding.webContainer.setDownloadListener { url, _, _, _, _ ->
            val intent = Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        binding.webContainer.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        binding.webContainer.loadUrl(argUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
