package com.demo.sprintit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.demo.sprintit.navigation.navGraph
import com.example.compose.AppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme  {
                val navController = rememberNavController()
                NavHost(navController = navController, navGraph(navController))
        }
    }
    }
}
