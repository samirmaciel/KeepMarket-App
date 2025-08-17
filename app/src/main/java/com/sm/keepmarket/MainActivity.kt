package com.sm.keepmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sm.keepmarket.components.BottomMenu
import com.sm.keepmarket.presentation.marketList.MarketListView
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.KeepMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepMarketTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Background,
                    bottomBar = { BottomMenu() }) { padding ->

                    MarketListView(padding)

                }
            }
        }
    }
}

