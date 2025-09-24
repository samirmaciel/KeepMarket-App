package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.theme.ButtonDefault

@Composable
fun BottomMenu() {

    var selectedIndex by remember { mutableStateOf(0) }
    val navController = LocalNavHostController.current

    navController.addOnDestinationChangedListener { navController, destination, arguments ->

        when(destination.route){

            Dest.HomeView::class.qualifiedName -> {
                selectedIndex = 0
            }
            Dest.PantryListView.getRoute() -> {
                selectedIndex = 1
            }
            Dest.MarketListView.getRoute() -> {
                selectedIndex = 2
            }
            Dest.NotificationView::class.qualifiedName -> {
                selectedIndex = 3
            }
            Dest.SearchView::class.qualifiedName -> {
                selectedIndex = 4
            }
            else -> {

            }
        }
    }

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                if(selectedIndex != 0){
                    selectedIndex = 0
                    navController.navigate(Dest.HomeView)
                }
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.homeicon),
                    tint = if (selectedIndex == 0) MaterialTheme.colorScheme.primary else ButtonDefault,
                    contentDescription = "Home button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {

                if(selectedIndex != 1){
                    selectedIndex = 1
                    navController.navigate(Dest.PantryListSelectionView)
                }

            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.calendaricon),
                    tint = if (selectedIndex == 1) MaterialTheme.colorScheme.primary else ButtonDefault,
                    contentDescription = "Pantry list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {

                if(selectedIndex != 2){
                    selectedIndex = 2
                    navController.navigate(Dest.MarketListSelectionView)
                }

            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.marketlisticon),
                    tint = if (selectedIndex == 2) MaterialTheme.colorScheme.primary else ButtonDefault,
                    contentDescription = "Market list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {

                if(selectedIndex != 3){
                    selectedIndex = 3
                    navController.navigate(Dest.NotificationView)
                }

            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.notificationicon),
                    tint = if (selectedIndex == 3) MaterialTheme.colorScheme.primary else ButtonDefault,
                    contentDescription = "Notifications button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {

                if(selectedIndex != 4){
                    selectedIndex = 4
                    navController.navigate(Dest.SearchView)
                }

            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.searchicon),
                    tint = if (selectedIndex == 4) MaterialTheme.colorScheme.primary else ButtonDefault,
                    contentDescription = "Search button"
                )
            }
        }

    }
}