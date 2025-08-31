package com.sm.keepmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.sm.keepmarket.presentation.splash.SplashView
import com.sm.keepmarket.presentation.theme.KeepMarketTheme

val LocalNavHostController =
    staticCompositionLocalOf<NavHostController> { error("Error while creating NavHostController") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepMarketTheme {
                SplashView()
            }
        }
    }
}



