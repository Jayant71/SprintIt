package com.demo.sprintit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.sprintit.data.model.User as LocalUser
import com.demo.sprintit.presentation.repository.FireStoreRepository
import com.demo.sprintit.presentation.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class AuthUiState {
    data object Initial : AuthUiState()
    data object SignedIn : AuthUiState()
    data class Error(val message: String) : AuthUiState()
    data object Loading : AuthUiState()
    data object UserAdded : AuthUiState()

}

class AuthViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val authRepository = FirebaseAuthRepository()
    private val firestoreRepository = FireStoreRepository()

    init {
        if (authRepository.isSignedIn()) {
            _uiState.value = AuthUiState.SignedIn
        }
    }
    
    fun isSignedIn(): Boolean {
        return authRepository.isSignedIn()
    }
    
     fun signIn(email: String, password: String) {
         _uiState.value =  AuthUiState.Loading

          try {
             authRepository.signIn(email, password).addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                        _uiState.value = AuthUiState.SignedIn
                 } else {
                     _uiState.value = AuthUiState.Error(task.exception?.message.toString())
                 }
             }
         } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Unknown error")
         }

    }

    fun signUp(email: String, password: String) {
        _uiState.value =  AuthUiState.Loading

        try {
            authRepository.signUp(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = AuthUiState.SignedIn
                } else {
                    _uiState.value = AuthUiState.Error(task.exception?.message.toString())
                }
            }
        } catch (e: Exception) {
            _uiState.value = AuthUiState.Error(e.message ?: "Unknown error")
        }

    }

     fun signOut() {
        authRepository.signOut()
    }

    fun addUser(user: LocalUser){
        _uiState.value =  AuthUiState.Loading

        try {
            firestoreRepository.addUser(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = AuthUiState.UserAdded
                } else {
                    _uiState.value = AuthUiState.Error(task.exception?.message.toString())
                }
            }
        } catch (e: Exception) {
            _uiState.value = AuthUiState.Error(e.message ?: "Unknown error")
        }
    }
}