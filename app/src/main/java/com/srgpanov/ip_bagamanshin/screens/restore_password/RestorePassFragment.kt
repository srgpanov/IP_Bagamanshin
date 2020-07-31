package com.srgpanov.ip_bagamanshin.screens.restore_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.srgpanov.ip_bagamanshin.databinding.FragmentRestorePassBinding
import com.srgpanov.ip_bagamanshin.other.showToast

class RestorePassFragment : Fragment() {
    private var _binding: FragmentRestorePassBinding? = null
    private val binding: FragmentRestorePassBinding
        get() = _binding!!
    private val viewModel: RestorePassViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestorePassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViews()
    }

    private fun setupViews() {
        binding.etEmail.setText(viewModel.email.value)
    }

    private fun setupListeners() {
        binding.btnRestore.setOnClickListener {
            requireActivity().showToast("Restore pass for email=${binding.etEmail.text}")
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveFields(
            binding.etEmail.text.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
