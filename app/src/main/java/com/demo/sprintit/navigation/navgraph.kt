package com.demo.sprintit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.demo.sprintit.presentation.ui.screens.addnew.AddNewProjectScreen
import com.demo.sprintit.presentation.ui.screens.authentication.CompleteProfileScreen
import com.demo.sprintit.presentation.ui.screens.authentication.SignInScreen
import com.demo.sprintit.presentation.ui.screens.authentication.SignUpScreen
import com.demo.sprintit.presentation.ui.screens.homescreen.HomeScreen
import com.demo.sprintit.presentation.ui.screens.landing.LandingScreen
import com.demo.sprintit.presentation.viewmodel.AuthViewModel
import kotlinx.serialization.Serializable

@Serializable object Auth
@Serializable object Landing
@Serializable object SignIn
@Serializable object SignUp
@Serializable
data class CompleteProfile(val email: String = "")
@Serializable object Home
@Serializable object HomeRoute
@Serializable object AddNewProject

@Composable
fun navGraph(
    navController: NavHostController = rememberNavController(),
    viwModel: AuthViewModel = viewModel()
): NavGraph {
    val navGraph = remember(navController) {
        navController.createGraph(startDestination = if (viwModel.isSignedIn()) HomeRoute else Auth) {

//        navController.createGraph(startDestination = Auth) {

            navigation<Auth>(startDestination = Landing) {
                composable<Landing> {
                    LandingScreen(

                        onNavigateToSignIn = {
                            navController.navigate(SignIn)

                        },
                        )
                }
                composable<SignIn> {
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
                composable<SignUp> {
                    SignUpScreen(
                        onNavigateToSignIn = {
                            navController.navigate(SignIn) {
                                popUpTo(SignUp) { inclusive = true }
                            }
                        },
                        onNavigateToCopleteProfile = { email ->
                            navController.navigate(CompleteProfile(email = email.toString())){

                                popUpTo(SignUp) { inclusive = true }
                            }
                        }
                    )
                }
                composable<CompleteProfile> { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email")
                     CompleteProfileScreen(
                         onNavigateToHome = {
                             navController.navigate(Home) {
                                    popUpTo(Landing) { inclusive = true }
                             }
                         },
                         email = email ?: ""
                     )
                }
            }

            navigation<HomeRoute>(startDestination = Home) {
                composable<Home> {
                    HomeScreen(
                        onLogout = {
                            navController.navigate(Auth) {
                                popUpTo(Home) { inclusive = true }
                            }
                        },
                        onNavigateToAddNewProject = {
                            navController.navigate(AddNewProject)
                        }
                    )
                }
                composable<AddNewProject> {
                    AddNewProjectScreen(
                        onNavigateToHome = {
                            navController.popBackStack()
                        }
                    )
                }
            }



        }
    }
    return navGraph
}