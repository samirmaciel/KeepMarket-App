package com.sm.keepmarket.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sm.keepmarket.presentation.components.BottomMenu

@Composable
fun MainNavigation() {

    val navHostController = rememberNavController()

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }


    CompositionLocalProvider(
        LocalNavHostController provides navHostController,
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 600)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(durationMillis = 600)
                    )
                ) { BottomMenu() }
            }) { padding ->

            AppNavigation(padding)
        }
    }
}