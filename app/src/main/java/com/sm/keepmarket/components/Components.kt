package com.sm.keepmarket.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.FeaturedCard
import com.sm.keepmarket.domain.Highlight
import com.sm.keepmarket.domain.MarketItem
import com.sm.keepmarket.domain.Notification
import com.sm.keepmarket.domain.PantryItem
import com.sm.keepmarket.domain.Route
import com.sm.keepmarket.domain.SearchItem
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CheckMarketItemModal
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.ButtonDefault
import java.math.BigDecimal

@Composable
fun BottomMenu() {

    var selectedIndex by remember { mutableStateOf(0) }
    val navController = LocalNavHostController.current

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Background
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                selectedIndex = 0
                navController.navigate(Dest.HomeView)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.homeicon),
                    tint = if (selectedIndex == 0) Blue else ButtonDefault,
                    contentDescription = "Home button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                selectedIndex = 1
                navController.navigate(Dest.PantryListView)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.calendaricon),
                    tint = if (selectedIndex == 1) Blue else ButtonDefault,
                    contentDescription = "Pantry list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                selectedIndex = 2
                navController.navigate(Dest.MarketListView)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.marketlisticon),
                    tint = if (selectedIndex == 2) Blue else ButtonDefault,
                    contentDescription = "Market list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                selectedIndex = 3
                navController.navigate(Dest.NotificationView)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.notificationicon),
                    tint = if (selectedIndex == 3) Blue else ButtonDefault,
                    contentDescription = "Notifications button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = {
                selectedIndex = 4
                navController.navigate(Dest.SearchView)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.searchicon),
                    tint = if (selectedIndex == 4) Blue else ButtonDefault,
                    contentDescription = "Search button"
                )
            }
        }

    }
}

@Composable
fun FeaturedCardButton(featuredCard: FeaturedCard, onClick: (Route) -> Unit) {

    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable(enabled = true, onClick = { })
    ) {
        Column(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .background(Blue, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp),
                    painter = painterResource(R.drawable.settingsicon),
                    tint = Color.White,
                    contentDescription = ""
                )
                Text(
                    featuredCard.featureType.value,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.name,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.lastUpdate.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 8.sp
            )
        }
    }
}

@Composable
fun HighlightItemView(highlight: Highlight) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = Blue, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.todolisticon),
                tint = Color.White,
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = highlight.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Icon(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(highlight.icon),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(10.dp))

                if (!highlight.description.isNullOrBlank()) {
                    Text(
                        modifier = Modifier,
                        text = highlight.description,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp
                    )
                }
            }
            Text(
                highlight.subTitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = ButtonDefault
            )
        }
    }
}

@Composable
fun MarketListItem(marketItem: MarketItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        var checked by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        var showCheckItem by remember { mutableStateOf(false) }

        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                showCheckItem = it
            }
        )

        if (showCheckItem) {
            CheckMarketItemModal(
                onDismiss = {
                    checked = false
                    showCheckItem = false },
                onFinish = { amount, price ->
                    showCheckItem = false
                })
        }

        Text(
            marketItem.name,
            style = MaterialTheme.typography.titleLarge.copy(textDecoration = if (checked) TextDecoration.LineThrough else null),
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )

        if (marketItem.price > BigDecimal.ZERO) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = marketItem.getFormattedTotalPrice(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = "● ${marketItem.amount}",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp
            )
        }

        Box() {
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.moreverticon),
                    tint = Color.Black,
                    contentDescription = "More button"
                )
            }

            DropdownMenu(
                modifier = Modifier.background(color = Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.editicon),
                        contentDescription = "Editar"
                    )
                }

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.deleteicon),
                        contentDescription = "Excluir"
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationItemView(notification: Notification) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp)) {
            Box(
                modifier = Modifier
                    .background(Blue, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.notificationicon),
                    tint = Color.Unspecified,
                    contentDescription = "Notification icon"
                )
            }
        }

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = notification.title, style = MaterialTheme.typography.labelMedium)
            Text(
                text = notification.subTitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )

        IconButton(onClick = {

        }) {
            Icon(
                painter = painterResource(R.drawable.deleteicon),
                tint = Color.Unspecified,
                contentDescription = "Delete notification button"
            )
        }

    }
}

@Composable
fun PantryListItem(pantryItem: PantryItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .background(color = pantryItem.getDueColor(), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        var expanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                pantryItem.name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp,
                color = Color.White
            )

            Text(
                pantryItem.getTimeLeft(),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )


        Text(
            modifier = Modifier.padding(end = 5.dp),
            text = "● ${pantryItem.amount}",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 15.sp,
            color = Color.White
        )


        Box() {
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.moreverticon),
                    tint = Color.White,
                    contentDescription = "More button"
                )
            }

            DropdownMenu(
                modifier = Modifier.background(color = Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.editicon),
                        contentDescription = "Editar"
                    )
                }

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.deleteicon),
                        contentDescription = "Excluir"
                    )
                }
            }
        }
    }

}

@Composable
fun SearchItemView(searchItem: SearchItem) {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(searchItem.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer(scaleY = if (expanded) -1f else 1f),
                painter = painterResource(R.drawable.arrowdownblackicon),
                tint = Color.Unspecified,
                contentDescription = "Arrow down"
            )
        }

        AnimatedVisibility(expanded) {
            Column {
                searchItem.items.forEach { item ->
                    HighlightItemView(item)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

        }
    }
}