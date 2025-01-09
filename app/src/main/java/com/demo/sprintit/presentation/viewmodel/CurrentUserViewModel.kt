package com.demo.sprintit.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.demo.sprintit.data.model.User as LocalUser

sealed class AddProjectUiState {
    data object Initial: AddProjectUiState()
    data object Loading: AddProjectUiState()
    data class Success(val user: LocalUser): AddProjectUiState()
    data class Error(val message: String): AddProjectUiState()
}

class CurrentUserViewModel: ViewModel() {
    private val _currentUser = MutableLiveData(LocalUser())
    val currentUser: LiveData<LocalUser> = _currentUser
    private val _uiState = MutableStateFlow<AddProjectUiState>(AddProjectUiState.Initial)
    val uiState: MutableStateFlow<AddProjectUiState> = _uiState

    init {
    }

    fun getUser(user: LocalUser) {
        _currentUser.value = user
    }
}