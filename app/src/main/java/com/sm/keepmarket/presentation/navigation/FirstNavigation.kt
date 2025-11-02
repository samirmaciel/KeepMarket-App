package com.sm.keepmarket.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sm.keepmarket.presentation.navigation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.login.LoginView
import com.sm.keepmarket.presentation.register.RegisterView
import com.sm.keepmarket.presentation.splash.SplashView

@Composable
fun FirstNavigation() {
    val navController = LocalNavHostController.current

    NavHost(navController = navController, startDestination = Dest.SplashView) {

        composable<Dest.LoginView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
        {
            LoginView()
        }

        composable<Dest.RegisterView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            RegisterView()
        }

        composable<Dest.SplashView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            SplashView()
        }

        composable<Dest.MainNavigation>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            MainNavigation()
        }

    }
}