package com.srgpanov.ip_bagamanshin.screens.authorization

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.srgpanov.ip_bagamanshin.App
import com.srgpanov.ip_bagamanshin.R
import com.srgpanov.ip_bagamanshin.databinding.FragmentAuthBinding
import com.srgpanov.ip_bagamanshin.other.showToast


class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding
        get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    private val navController by lazy { findNavController() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.injectAuthFragment(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViews()
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val exitDialog = ExitDialog()
                    exitDialog.exitCallback = object : ExitDialog.OnExitCallback {
                        override fun onExit(exit: Boolean) {
                            if(exit){
                                requireActivity().finish()
                            }else{
                                exitDialog.dismiss()
                            }
                        }
                    }
                    exitDialog.show(childFragmentManager, ExitDialog.TAG)
                }
            })
    }

    private fun setupViews() {
        binding.etEmail.setText(viewModel.email.value)
        binding.etPassword.setText(viewModel.password.value)
        binding.checkboxRemember.isChecked =viewModel.rememberLogin.value?:false
    }

    private fun setupListeners() {
        binding.tvRegister.setOnClickListener {
            navController.navigate(R.id.action_authFragment_to_registerFragment)
        }
        binding.tvRestore.setOnClickListener {
            navController.navigate(R.id.action_authFragment_to_restorePassFragment)
        }

        binding.btnLogin.setOnClickListener {
            requireActivity().showToast("Login with save user auth = ${binding.checkboxRemember.isChecked}")
        }
        binding.btnFacebook.setOnClickListener {
            requireActivity().showToast("Login with Facebook")
        }
        binding.btnGoogle.setOnClickListener {
            requireActivity().showToast("Login with Google")
        }

    }

    override fun onStop() {
        super.onStop()
        viewModel.saveFields(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),
            binding.checkboxRemember.isChecked
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
