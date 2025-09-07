package com.sm.keepmarket.presentation

import kotlinx.serialization.Serializable
@Serializable
sealed interface Dest {

    @Serializable
    data object SplashView : Dest
    @Serializable
    data object HomeView : Dest

    @Serializable
    data class MarketListView(val id: String) : Dest {
        companion object{
            fun getRoute(): String {
                return MarketListView::class.qualifiedName + "/{id}"
            }
        }
    }

    @Serializable
    data class PantryListView(val id: String) : Dest{
        companion object{
            fun getRoute(): String {
                return PantryListView::class.qualifiedName + "/{id}"
            }
        }
    }

    @Serializable
    data object MarketListSelectionView : Dest
    @Serializable
    data object PantryListSelectionView : Dest

    @Serializable
    data object NotificationView : Dest

    @Serializable
    data object SettingsView : Dest

    @Serializable
    data object SearchView : Dest
}