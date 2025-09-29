package com.sm.keepmarket.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sm.keepmarket.FirstNavigation
import com.sm.keepmarket.presentation.theme.KeepMarketTheme

val LocalNavHostController =
    staticCompositionLocalOf<NavHostController> { error("Error while creating NavHostController") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepMarketTheme {

                val navHostController = rememberNavController()

                CompositionLocalProvider(
                    LocalNavHostController provides navHostController,
                ) {
                    FirstNavigation()
                }
            }
        }
    }
}



