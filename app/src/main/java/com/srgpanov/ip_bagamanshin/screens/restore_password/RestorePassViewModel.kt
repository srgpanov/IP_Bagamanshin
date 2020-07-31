package com.srgpanov.ip_bagamanshin.screens.restore_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.srgpanov.ip_bagamanshin.screens.authorization.AuthViewModel

class RestorePassViewModel constructor(private val state: SavedStateHandle) : ViewModel() {
    private val _email = state.getLiveData<String>(FIELD_EMAIL)
    val email: LiveData<String>
        get() = _email


    fun saveFields(email: String) {
        state.set(FIELD_EMAIL,email)

    }
    companion object {
        private const val FIELD_EMAIL = "FIELD_EMAIL"
    }
}
