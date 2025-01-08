package com.demo.sprintit.presentation.ui.screens.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.demo.sprintit.R

@Composable
fun HomeCard(
    horizontalPadding: Dp = 10.dp
) {
    Card(
        modifier = Modifier
            .padding(horizontalPadding + 8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp,)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cardbg),
                        contentDescription = "Project",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.run {
                            fillMaxWidth()

                        }
                    )
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "SprintIt Mobile App Project",
                            style = TextStyle(
                                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            ),
                        )
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Add",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )

                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Timeline: 2 weeks",
                        )
                        Text(
                            "Progress: 83%",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.83f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .size(height = 6.dp, width = 200.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.Gray.copy(alpha = 0.5f)

                    )
                }
            }
        }
    }
}