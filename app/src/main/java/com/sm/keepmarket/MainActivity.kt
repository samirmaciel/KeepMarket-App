package com.sm.keepmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sm.keepmarket.components.BottomMenu
import com.sm.keepmarket.presentation.AppNavigation
import com.sm.keepmarket.presentation.marketList.MarketListView
import com.sm.keepmarket.presentation.notifications.NotificationView
import com.sm.keepmarket.presentation.pantryList.PantryListView
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.KeepMarketTheme


val LocalNavHostController =
    staticCompositionLocalOf<NavHostController> { error("Error while creating NavHostController") }
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()

            KeepMarketTheme {

                CompositionLocalProvider(
                    LocalNavHostController provides navHostController,
                ) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Background,
                        bottomBar = { BottomMenu() }) { padding ->

                        AppNavigation(padding)

                    }
                }
            }
        }
    }
}

