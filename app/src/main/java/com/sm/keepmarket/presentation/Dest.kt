package com.sm.keepmarket.presentation

import kotlinx.serialization.Serializable
@Serializable
sealed interface Dest {
    @Serializable
    data object HomeView : Dest

    @Serializable
    data object MarketListView : Dest

    @Serializable
    data object PantryListView : Dest

    @Serializable
    data object NotificationView : Dest

    @Serializable
    data object SettingsView : Dest

    @Serializable
    data object SearchView : Dest
}