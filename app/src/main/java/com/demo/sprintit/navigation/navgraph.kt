package com.demo.sprintit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.demo.sprintit.presentation.ui.screens.authentication.SignInScreen
import com.demo.sprintit.presentation.ui.screens.authentication.SignUpScreen
import com.demo.sprintit.presentation.ui.screens.homescreen.HomeScreen
import com.demo.sprintit.presentation.ui.screens.landing.LandingScreen
import com.demo.sprintit.presentation.viewmodel.SignInViewmodel
import kotlinx.serialization.Serializable

@Serializable object Auth
@Serializable object Landing
@Serializable object SignIn
@Serializable object SignUp
@Serializable object Home


@Composable
fun navGraph(
    navController: NavHostController = rememberNavController(),
    viwModel: SignInViewmodel = viewModel()
): NavGraph {
    val navGraph = remember(navController) {
        navController.createGraph(startDestination = if (viwModel.isSignedIn()) Home else Auth) {

            navigation<Auth>(startDestination = Landing) {
                composable<Landing>() {
                    LandingScreen(

                        onNavigateToSignIn = {
                            navController.navigate(SignIn)

                        },
                        )
                }
                composable<SignIn>() {
                    SignInScreen(
                        onNavigateToSignUp = {
                            navController.navigate(SignUp) {
                                popUpTo(SignIn) { inclusive = true }
                            }
                        },
                        onSignIn = {
                            navController.navigate(Home) {
                                popUpTo(Landing) { inclusive = true }
                            }
                        }
                    )
                }
                composable<SignUp>() {
                    SignUpScreen(
                        onNavigateToSignIn = {
                            navController.navigate(SignIn) {
                                popUpTo(SignUp) { inclusive = true }
                            }
                        },
                        onNavigateToHome = {
                            navController.navigate(Home) {
                                popUpTo(SignUp) { inclusive = true }
                            }
                        }
                    )
                }
            }

                composable<Home>() {
                    HomeScreen(
                        onLogout = {
                            navController.navigate(Auth) {
                                popUpTo(Home) { inclusive = true }
                            }
                        }
                    )
                }
        }
    }
    return navGraph
}