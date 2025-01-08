package com.demo.sprintit.presentation.ui.screens.homescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.sprintit.R
import com.demo.sprintit.presentation.ui.screens.homescreen.components.HomeCard
import com.demo.sprintit.presentation.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    onLogout: () -> Unit = {}
) {
    val isLoggingOut = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            HomeTopBar(
                onLogout = onLogout,
                onLogginOut = { value ->
                    isLoggingOut.value = value
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentPadding = PaddingValues(16.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    TabRowItem(
                        icon = Icons.Default.Home,
                        text = "Home",
                        isSelected = true
                    )
                    TabRowItem(
                        icon = Icons.Default.AddCircle,
                        isSelected = false
                    )
                    TabRowItem(
                        icon = Icons.Default.Settings,
                        text = "Settings",
                        isSelected = false
                    )
                }
            }
        }
    ) {  innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TabRowItem(icon: ImageVector, text: String = "", isSelected: Boolean, onTap: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onTap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier.size(if (text.isEmpty()) 48.dp else 32.dp)
        )
        Text(
            text = text,
            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
            color = if (isSelected) Color.Black else Color.Gray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    viewmodel: AuthViewModel = viewModel(),
    onLogout: () -> Unit = {},
    logginOut: Boolean = false,
    onLogginOut: (Boolean) -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(text = "SprintIt", style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 25.sp ), modifier = Modifier.padding(start = 16.dp))
        },

        modifier = modifier.padding(horizontal = 16.dp),
        actions = {
            FilledIconButton(
                onClick = {
                    Log.d("HomeScreen", "Clicked on Notifications")
                },
                colors =
                IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(28.dp)
                )
            }
            FilledIconButton(
                onClick = {
                    Log.d("HomeScreen", "Clicked on Logout")
                    onLogginOut(true)
                        viewmodel.signOut()

                    onLogginOut(false)
                    onLogout()

                },
                colors =
                IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                    contentDescription = "Logout",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.appicon),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = CircleShape)
            )
        }

    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    val horizontalPadding = 6.dp
    val pagerState = rememberPagerState(pageCount = { 7 })
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontalPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Projects",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            )
            Text(
                text = "See all",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.clickable {
                    Log.d("HomeScreen", "Clicked on See all")
                }
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        HorizontalPager(
            state = pagerState,
        ) {
            repeat(7) {
                HomeCard(horizontalPadding = horizontalPadding)
            }
        }
    }
}