package com.demo.sprintit.presentation.ui.screens.addnew

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.demo.sprintit.R
import com.demo.sprintit.presentation.viewmodel.CurrentUserViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewProjectScreen(
    onNavigateToHome: () -> Unit = {},
    viewModel: CurrentUserViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
    title = {  },
    navigationIcon = {
        IconButton(onClick = onNavigateToHome) {
            Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
        }
    },
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
        scrolledContainerColor = Color.Transparent,
    ),
//    modifier = ,
)
        },

    ) { innerPadding ->
        AddNewProjectContent(
            modifier = Modifier.padding(innerPadding),
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewProjectContent(
    modifier: Modifier = Modifier,
    ) {
    val selectedImage = remember { mutableStateOf<Uri?>(null) }
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            selectedImage.value = uri
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    val context = LocalContext.current

    val datePickerState = rememberDatePickerState()
    val textFieldDefaults = TextFieldDefaults.colors(
        focusedContainerColor =Color.White.copy(alpha = 0.1f)
    )
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val modifierTextField = Modifier.fillMaxWidth().border(1.dp, Color.Black, MaterialTheme.shapes.small).clip(MaterialTheme.shapes.small)
    Column (
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier.padding(horizontal = 30.dp).fillMaxWidth().verticalScroll(
            state = rememberScrollState(),
            enabled = true,
        )
    ) {
        Text(
            text = "Add New Project",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)
            )
        Box(
            modifier = Modifier.fillMaxWidth().clickable(onClick = {
                pickMedia.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            }).border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.small).clip(MaterialTheme.shapes.small).height(200.dp)
        ){
            if (selectedImage.value != null) {

                Image(
                    painter = rememberAsyncImagePainter(selectedImage.value),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize().clip(MaterialTheme.shapes.small)
                    )

            } else {
                Text(
                    text = "Select Image",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp).wrapContentSize(Alignment.Center)
                    )
            }
        }
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            colors = textFieldDefaults,
            label = { Text("Project Name") },
            modifier = modifierTextField
            )
        TextField(
            value = description.value,
            onValueChange = {
                description.value = it
            },
            colors = textFieldDefaults,
            label = { Text("Project Description") },
            modifier = modifierTextField
            )
        DatePicker(
            state = datePickerState,
            title = { Text("Select Deadline", style = MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.Underline)) },
            modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.small).clip(MaterialTheme.shapes.small).padding(8.dp),
        )
        Button(
            onClick = {
                if (name.value.trim().isNotEmpty() && description.value.trim().isNotEmpty() && datePickerState.selectedDateMillis != null) {
                    Log.d("AddNewProject", "Name: ${name.value}, Description: ${description.value}, Deadline: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                        Date(datePickerState.selectedDateMillis!!)
                    )}")
                } else {
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Add Project",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                )
        }
        Spacer(modifier = Modifier.padding(16.dp))
    }

}
