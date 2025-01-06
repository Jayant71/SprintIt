package com.demo.sprintit.presentation.ui.screens.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.sprintit.utils.validateEmail
import com.demo.sprintit.utils.validatePassword

@Composable
fun SignUpScreen(
    onNavigateToSignIn: () -> Unit,
) {
    Scaffold (

    ) { innerPadding ->
        SignUpContent(
            modifier = Modifier.padding(innerPadding),
            onNavigateToSignIn = onNavigateToSignIn
        )
    }
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit,
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

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
            isError = confirmPassword.value.isNotEmpty() && confirmPassword.value != password.value,
        )


        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {

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
