package com.demo.sprintit.presentation.ui.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.sprintit.presentation.viewmodel.SignInUiState
import com.demo.sprintit.presentation.viewmodel.SignInViewmodel
import com.demo.sprintit.utils.validateEmail
import com.demo.sprintit.utils.validatePassword

@Composable
fun SignUpScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit = {},
    viewmodel: SignInViewmodel = viewModel()
) {
    val uiState = viewmodel.uiState.collectAsState()

    Scaffold (
        modifier = Modifier.imePadding()
    ) { innerPadding ->
        SignUpContent(
            modifier = Modifier.padding(innerPadding),
            onNavigateToSignIn = onNavigateToSignIn,
            viewmodel = viewmodel,
            state = uiState.value,
            onNavigateToHome = onNavigateToHome

        )
    }
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewmodel: SignInViewmodel,
    state: SignInUiState
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is SignInUiState.SignedIn -> {
                onNavigateToHome()
            }
            is SignInUiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    when (state) {
        is SignInUiState.Initial, is SignInUiState.Error -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Text(text = "Let's Sign you In")
                OutlinedTextField(value = email.value, onValueChange = {
                    email.value = it
                }, label = { Text("Email") },
                    shape = CircleShape.copy(all = CornerSize(16.dp)), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                        focusedContainerColor = Color.Gray.copy(alpha = 0.05f),
                    ),

                    isError = email.value.isNotEmpty() && !validateEmail(email.value),

                    )

                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(value = password.value, onValueChange = {
                    password.value = it
                }, label = { Text("Password") },
                    shape = CircleShape.copy(all = CornerSize(16.dp)), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                        focusedContainerColor = Color.Gray.copy(alpha = 0.05f),
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = password.value.isNotEmpty() && !validatePassword(password.value),

                    )


                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(value = confirmPassword.value, onValueChange = {
                    confirmPassword.value = it

                }, label = { Text("Confirm Password") },
                    shape = CircleShape.copy(all = CornerSize(16.dp)), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                        focusedContainerColor = Color.Gray.copy(alpha = 0.05f),
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = confirmPassword.value.isNotEmpty() && confirmPassword.value != password.value,
                )


                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        viewmodel.signUp(email.value, password.value)

                    }
                ) {
                    Text(text = "Sign Up")
                }
                Text(
                    text = "Already Have an account? Sign In",
                    modifier = Modifier.clickable {
                        onNavigateToSignIn()
                    }
                )

            }
        }
        is SignInUiState.Loading,  -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
        }
    }


}
