package com.bibekgupta.cleanarchitecture.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibekgupta.cleanarchitecture.utility.Resource
import com.bibekgupta.cleanarchitecture.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bibekgupta.cleanarchitecture.domain.model.login.LoginResponse

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {



    private val _loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading())
    val loginState: StateFlow<Resource<LoginResponse>> = _loginState

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()
            val result = loginUseCase(username, password)
            _loginState.value = result
            if (result is Resource.Success) {
                _navigateToHome.value = true // Trigger navigation on success
            }
        }
    }

    fun resetNavigation() {
        _navigateToHome.value = false
    }

}


