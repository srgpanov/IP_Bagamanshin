package com.srgpanov.ip_bagamanshin.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.srgpanov.ip_bagamanshin.screens.authorization.AuthViewModel

class RegisterViewModel constructor(private val state: SavedStateHandle): ViewModel() {
    private val _email = state.getLiveData<String>(FIELD_EMAIL)
    val email: LiveData<String>
        get() = _email

    private val _name = state.getLiveData<String>(FIELD_NAME)
    val name: LiveData<String>
        get() = _name

    private val _password = state.getLiveData<String>(FIELD_PASSWORD)
    val password: LiveData<String>
        get() = _password

    fun saveFields(email: String, password: String, name: String) {
        state.set(FIELD_EMAIL, email)
        state.set(FIELD_NAME, name)
        state.set(FIELD_PASSWORD, password)
    }

    companion object {
        private const val FIELD_EMAIL = "FIELD_EMAIL"
        private const val FIELD_NAME = "FIELD_NAME"
        private const val FIELD_PASSWORD = "FIELD_PASSWORD"
    }
}
