package com.sm.keepmarket.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sm.keepmarket.presentation.navigation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.home.HomeView
import com.sm.keepmarket.presentation.login.LoginView
import com.sm.keepmarket.presentation.marketList.MarketListSelectionView
import com.sm.keepmarket.presentation.marketList.MarketListView
import com.sm.keepmarket.presentation.notifications.NotificationView
import com.sm.keepmarket.presentation.pantryList.PantryListSelectionView
import com.sm.keepmarket.presentation.pantryList.PantryListView
import com.sm.keepmarket.presentation.register.RegisterView
import com.sm.keepmarket.presentation.search.SearchView
import com.sm.keepmarket.presentation.splash.SplashView

@Composable
fun AppNavigation(paddingValues: PaddingValues, isBottomBarVisible: (Boolean) -> Unit) {
    val navController = LocalNavHostController.current

    NavHost(navController = navController, startDestination = Dest.SplashView) {

        composable<Dest.SplashView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
        {
            isBottomBarVisible(false)
            SplashView()
        }

        composable<Dest.LoginView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
        {
            isBottomBarVisible(false)
            LoginView()
        }

        composable<Dest.RegisterView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
        {
            isBottomBarVisible(false)
            RegisterView()
        }

        composable<Dest.HomeView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) })
         {
             isBottomBarVisible(true)
            HomeView(paddingValues)
        }

        composable<Dest.MarketListView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            val args = it.toRoute<Dest.MarketListView>()
            MarketListView(marketListID = args.id, paddingValues = paddingValues)
        }

        composable<Dest.SettingsView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

        }

        composable<Dest.PantryListView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {

            val args = it.toRoute<Dest.PantryListView>()
            PantryListView(pantryListID = args.id, paddingValues = paddingValues)
        }

        composable<Dest.SearchView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {
            SearchView(paddingValues)
        }

        composable<Dest.NotificationView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {
            NotificationView(paddingValues)
        }

        composable<Dest.MarketListSelectionView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {
            MarketListSelectionView(paddingValues)
        }

        composable<Dest.PantryListSelectionView>(
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) },
            popEnterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { it }) }) {
            PantryListSelectionView(paddingValues)
        }

    }
}