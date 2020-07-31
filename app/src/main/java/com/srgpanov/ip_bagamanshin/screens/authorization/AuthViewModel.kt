package com.srgpanov.ip_bagamanshin.screens.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class AuthViewModel constructor(private val state: SavedStateHandle) : ViewModel() {
    private val _email = state.getLiveData<String>(FIELD_EMAIL)
    val email: LiveData<String>
        get() = _email

    private val _password = state.getLiveData<String>(FIELD_PASSWORD)
    val password: LiveData<String>
        get() = _password

    private val _rememberLogin = state.getLiveData<Boolean>(FIELD_REMEMBER_LOGIN)
    val rememberLogin: LiveData<Boolean>
        get() = _rememberLogin

    fun saveFields(email: String, password: String, checkBox: Boolean) {
        state.set(FIELD_EMAIL, email)
        state.set(FIELD_PASSWORD, password)
        state.set(FIELD_REMEMBER_LOGIN, checkBox)
    }


    companion object {
        private const val FIELD_EMAIL = "FIELD_EMAIL"
        private const val FIELD_PASSWORD = "FIELD_PASSWORD"
        private const val FIELD_REMEMBER_LOGIN = "FIELD_REMEMBER_LOGIN"
    }
}
