package com.sm.keepmarket

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.login.LoginView
import com.sm.keepmarket.presentation.splash.SplashView

@Composable
fun FirstNavigation() {
    val navController = LocalNavHostController.current

    NavHost(navController = navController, startDestination = Dest.LoginView) {

        composable<Dest.LoginView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
        {
            LoginView()
        }

        composable<Dest.SplashView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            SplashView()
        }

    }
}