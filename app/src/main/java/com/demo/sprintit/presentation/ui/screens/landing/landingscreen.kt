package com.demo.sprintit.presentation.ui.screens.landing

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.demo.sprintit.R
import com.demo.sprintit.presentation.ui.components.LandingButton
import com.demo.sprintit.presentation.ui.components.LandingPages
import kotlinx.coroutines.launch

@Composable
fun LandingScreen(
    onNavigateToSignIn: () -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 },
        initialPageOffsetFraction = 0f
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val coroutineScope = rememberCoroutineScope()
        val buttonModifier = Modifier
            .padding(end = 8.dp)
            .fillMaxWidth(0.9f)
            .height(48.dp)
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .padding(vertical = 20.dp)
        ) { page ->
            val text1 = if (page == 0) "Organize your Tasks & Projects Easily" else if (page == 1) "Collaborate with your Team" else "Everything You Can Do in the App"
            val contentDescription = if (page == 0) "SprintIt is a project management tool that helps you organize your work and collaborate with your team." else if (page == 1) "SprintIt is a project management tool that helps you organize your work and collaborate with your team." else "SprintIt is a project management tool that helps you organize your work and collaborate with your team."
            val painterResource = if (page == 0) R.drawable.landing1 else if (page == 1) R.drawable.landing2 else R.drawable.landing3

            LandingPages(
                page = page,
                text1 = text1,
                contentDescription = contentDescription,
                painterResource = painterResource
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().size(height = 24.dp, width = 0.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { index ->
                Box(

                    modifier = Modifier
                        .padding(4.dp)
                        .size(
                            if (pagerState.currentPage == index) 16.dp else 8.dp
                        )
                        .background(
                            color = if (pagerState.currentPage == index) Color.Black.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.5f),
                            shape = CircleShape
                        ).align(Alignment.CenterVertically),

                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LandingButton(
                text = if (pagerState.currentPage == 2) "Sign In" else "Next",
                onClick = {
                    if (pagerState.currentPage == 2) {
                        onNavigateToSignIn()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = tween(durationMillis = 500))
                        }
                    }
                },
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary

                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (pagerState.currentPage!= 2) LandingButton(
                text = "Skip",
                onClick = onNavigateToSignIn,
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    contentColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

