package com.demo.sprintit.presentation.ui.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.sprintit.data.model.User as LocalUser
import com.demo.sprintit.presentation.viewmodel.AuthUiState
import com.demo.sprintit.presentation.viewmodel.AuthViewModel
import java.util.UUID

@Composable
fun CompleteProfileScreen(
    onNavigateToHome: () -> Unit = {},
    viewModel: AuthViewModel = viewModel(),
    email: String
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold { innerPadding ->
        CompleteProfileContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            onNavigateToHome = onNavigateToHome,
            uiState = uiState.value,
            email_ = email
            )
    }
}

@Composable
fun CompleteProfileContent(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    onNavigateToHome: () -> Unit = {},
    uiState: AuthUiState,
    email_: String
) {
    val context = LocalContext.current
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf(email_) }



    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.UserAdded -> {
                onNavigateToHome()
            }
            is AuthUiState.Error -> {
                Toast.makeText(
                    context,
                    uiState.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    when (uiState) {
        is AuthUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is AuthUiState.Initial, is AuthUiState.Error -> {

        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                Text("What's your name?", style = TextStyle(fontSize = 32.sp))
                Spacer(modifier = Modifier.height(40.dp))
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label =  { Text("Name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                    ),
                    modifier = Modifier.border(
                        1.dp,
                        Color.Black,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp))).clip(RoundedCornerShape(corner = CornerSize(10.dp))),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        viewModel.addUser(LocalUser(userId = UUID.randomUUID().toString(),name = name.value, email = email.value))
                    },
                ) {
                    Text("Complete Profile")
                }


            }
        }
    }
}