package com.sm.keepmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sm.keepmarket.components.BottomMenu
import com.sm.keepmarket.presentation.AppNavigation
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

                    var isVisibleBottomMenu by remember { mutableStateOf(false) }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Background,
                        bottomBar = {
                            AnimatedVisibility(isVisibleBottomMenu) { BottomMenu() }
                        }) { padding ->

                        AppNavigation(padding) {
                            isVisibleBottomMenu = it
                        }

                    }
                }
            }
        }
    }
}

