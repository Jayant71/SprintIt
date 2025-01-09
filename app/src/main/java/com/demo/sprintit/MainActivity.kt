package com.demo.sprintit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.demo.sprintit.navigation.navGraph
import com.example.compose.AppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(scrim = 1, darkScrim = 1)
        )
        setContent {
            AppTheme  {
                val navController = rememberNavController()
                NavHost(navController = navController, navGraph(navController))
        }
    }
    }
}
