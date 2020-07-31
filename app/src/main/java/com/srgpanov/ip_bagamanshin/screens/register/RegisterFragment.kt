package com.srgpanov.ip_bagamanshin.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.srgpanov.ip_bagamanshin.databinding.FragmentRegisterBinding
import com.srgpanov.ip_bagamanshin.other.showToast

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViews()
    }

    private fun setupViews() {
        binding.etEmail.setText(viewModel.email.value)
        binding.etName.setText(viewModel.name.value)
        binding.etPassword.setText(viewModel.password.value)
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            requireActivity().showToast("Register with params email=${ binding.etEmail.text} \n" +
                    "name=${ binding.etName.text}\n" +
                    "password=${ binding.etPassword.text} ")
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveFields(
            binding.etEmail.text.toString(),
            binding.etName.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
