package com.demo.sprintit.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.sprintit.presentation.repository.FirebaseAuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SignInUiState {
    data object Initial : SignInUiState()
    data object SignedIn : SignInUiState()
    data class Error(val message: String) : SignInUiState()
    data object Loading : SignInUiState()

}

class SignInViewmodel: ViewModel() {
    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Initial)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val authRepository = FirebaseAuthRepository()

    init {
        if (authRepository.isSignedIn()) {
            _uiState.value = SignInUiState.SignedIn
        }
    }
    
    fun isSignedIn(): Boolean {
        return authRepository.isSignedIn()
    }
    
     fun signIn(email: String, password: String) {
         _uiState.value =  SignInUiState.Loading

          try {
             authRepository.signIn(email, password).addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                        _uiState.value = SignInUiState.SignedIn
                 } else {
                     _uiState.value = SignInUiState.Error(task.exception?.message.toString())
                 }
             }
         } catch (e: Exception) {
                _uiState.value = SignInUiState.Error(e.message ?: "Unknown error")
         }

    }

    fun signUp(email: String, password: String) {
        _uiState.value =  SignInUiState.Loading

        try {
            authRepository.signUp(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = SignInUiState.SignedIn
                } else {
                    _uiState.value = SignInUiState.Error(task.exception?.message.toString())
                }
            }
        } catch (e: Exception) {
            _uiState.value = SignInUiState.Error(e.message ?: "Unknown error")
        }

    }

     fun signOut() {
        authRepository.signOut()
    }
}