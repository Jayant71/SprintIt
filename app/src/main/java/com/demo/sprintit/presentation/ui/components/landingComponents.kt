package com.demo.sprintit.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LandingButton(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Text(text = text, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily)
    }
}

@Composable
fun LandingPages(
    page: Int,
    text1: String,
    contentDescription: String,
    painterResource: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = painterResource),
                contentDescription = null,
            )
            Column {
                Text(
                    text = text1,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = contentDescription,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                )
            }
        }
    }
}