package com.demo.sprintit.presentation.ui.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.sprintit.presentation.viewmodel.AuthUiState
import com.demo.sprintit.presentation.viewmodel.AuthViewModel

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit,
    onSignIn: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold (

            ) { innerPadding ->
            SignInContent(
                modifier = Modifier.padding(innerPadding),
                onNavigateToSignUp,
                onSignIn = onSignIn,
                viewModel = viewModel,
                uiState = uiState.value
            )
    }

}


@Composable
fun SignInContent(
    modifier: Modifier,
    onNavigateToSignUp: () -> Unit,
    onSignIn: () -> Unit,
    viewModel: AuthViewModel,
    uiState: AuthUiState
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.SignedIn -> {
                onSignIn()
            }
            is AuthUiState.Error -> {
                Toast.makeText(context, (uiState).message, Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }
    }

    when(uiState) {
        is AuthUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is AuthUiState.Initial, is AuthUiState.Error  -> {
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

                    )

                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(value = password.value, onValueChange = {
                    password.value = it
                }, label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = CircleShape.copy(all = CornerSize(16.dp)), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                        focusedContainerColor = Color.Gray.copy(alpha = 0.05f),
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        if (email.value.isEmpty() || password.value.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }
                        viewModel.signIn(email.value, password.value)

                    }
                ) {
                        Text(text = "Sign In")
                }
                Text(
                    text = "Don't have an account? Sign Up",
                    modifier = Modifier.clickable {
                        onNavigateToSignUp()
                    }
                )

            }
        }
        else -> {}


    }


}

