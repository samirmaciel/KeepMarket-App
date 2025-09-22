package com.sm.keepmarket.components

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.domain.model.NotificationItem
import com.sm.keepmarket.domain.model.PantryItem
import com.sm.keepmarket.domain.model.SearchItem
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CheckMarketItemModal
import com.sm.keepmarket.presentation.modal.EditMarketItemModal
import com.sm.keepmarket.presentation.modal.EditPantryItemModal
import com.sm.keepmarket.presentation.theme.ButtonDefault
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.HighlightType
import java.math.BigDecimal

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

@Composable
fun FeaturedCardButton(featuredCard: FeaturedCard, onClick: (FeaturedCard) -> Unit) {

    var icon = if(featuredCard.featuredType.value == FeaturedType.MARKET.value) R.drawable.marketlisticon else R.drawable.calendaricon
    var typeLabel = if(featuredCard.featuredType.value == FeaturedType.MARKET.value) stringResource(R.string.title_market) else stringResource(R.string.title_pantry)

    Box(
        modifier = Modifier
            .clickable(enabled = true, onClick = { onClick(featuredCard) })
    ) {
        Column(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp),
                    painter = painterResource(icon),
                    tint = Color.White,
                    contentDescription = ""
                )
                Text(
                    typeLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.getFormattedLastUpdate(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 10.sp
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
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.analyticsicon),
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
fun MarketItemListView(
    marketItem: MarketItem,
    onEdit: (MarketItem) -> Unit,
    onDeleted: (MarketItem) -> Unit
) {

    var showEditItemModal by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(marketItem.isChecked) }
    var expandedMoreActions by remember { mutableStateOf(false) }
    var showCheckMarketItemModal by remember { mutableStateOf(false) }

    LaunchedEffect(marketItem.isChecked){
       checked = marketItem.isChecked
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                showCheckMarketItemModal = it

                if (!it) {
                    val newMarketItem = marketItem.copy(
                        isChecked = false
                    )
                    onEdit(newMarketItem)
                }
            }
        )

        if (showCheckMarketItemModal) {
            CheckMarketItemModal(
                marketItem = marketItem,
                onDismiss = {
                    checked = false
                    showCheckMarketItemModal = false
                },
                onFinish = { amount, price ->
                    val newMarketItem = marketItem.copy(
                        isChecked = checked,
                        amount = amount,
                        price = price
                    )

                    onEdit(newMarketItem)

                    showCheckMarketItemModal = false
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
                text = marketItem.getFormattedPrice(),
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
                expandedMoreActions = true
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
                expanded = expandedMoreActions,
                onDismissRequest = { expandedMoreActions = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            showEditItemModal = true
                            expandedMoreActions = false
                        }
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
                        .clickable { showDeleteDialog = true }
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

    if (showDeleteDialog) {
        AlertMessage(stringResource(R.string.message_delete_item_confirm), onDismiss = {
            showDeleteDialog = false
            expandedMoreActions = false
        }, onConfirm = {
            onDeleted(marketItem)
            showDeleteDialog = false
            expandedMoreActions = false
        })
    }

    if (showEditItemModal) {
        EditMarketItemModal(
            marketItem,
            onDismiss = { showEditItemModal = false },
            onFinish = { name, amount, price ->
                val newMarketItem = marketItem.copy(
                    name = name,
                    amount = amount,
                    price = price
                )

                onEdit(newMarketItem)
                showEditItemModal = false
            })
    }

}

@Composable
fun NotificationItemView(notificationItem: NotificationItem, onDeleteItem: (NotificationItem) -> Unit) {

    var showDeleteAlert by remember { mutableStateOf(false) }

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
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
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
            Text(text = notificationItem.title, style = MaterialTheme.typography.labelMedium)
            Text(
                text = notificationItem.subTitle,
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
            showDeleteAlert = true
        }) {
            Icon(
                painter = painterResource(R.drawable.deleteicon),
                tint = Color.Unspecified,
                contentDescription = "Delete notification button"
            )
        }

    }

    if(showDeleteAlert){
        AlertDialog(
            onDismissRequest = { showDeleteAlert = false },
            title = { Text(stringResource(R.string.label_confirmation)) },
            text = { Text(stringResource(R.string.message_delete_item_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteItem(notificationItem)
                    showDeleteAlert = false
                }) {
                    Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteAlert = false
                }) {
                    Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }
}

@Composable
fun PantryItemListView(
    pantryItem: PantryItem,
    onEdit: (PantryItem) -> Unit,
    onDeleted: (PantryItem) -> Unit
) {

    var showEditItemModal by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var expandedMoreActions by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .background(color = pantryItem.getDueColor(), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

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
                expandedMoreActions = true
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
                expanded = expandedMoreActions,
                onDismissRequest = { expandedMoreActions = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            showEditItemModal = true
                            expandedMoreActions = false
                        }
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
                        .clickable { showDeleteDialog = true }
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

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.label_confirmation)) },
            text = { Text(stringResource(R.string.message_delete_item_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    onDeleted(pantryItem)
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }

    if (showEditItemModal) {
        EditPantryItemModal(
            pantryItem,
            onDismiss = { showEditItemModal = false },
            onFinish = { name, amount, dueDate ->
                val newPantryItem = pantryItem.copy(
                    name = name,
                    amount = amount,
                    dueDate = dueDate
                )

                onEdit(newPantryItem)
                showEditItemModal = false
            })
    }
}

@Composable
fun SearchItemView(searchItem: SearchItem, onExpanded: (Boolean) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable { onExpanded(!searchItem.expanded) },
            verticalAlignment = Alignment.CenterVertically
        ) {

            val resourceTitle = when(searchItem.title){
                HighlightType.PRICE_INCREASE.value -> {
                    stringResource(R.string.title_price_increase)
                }
                HighlightType.PRICE_DECREASE.value -> {
                    stringResource(R.string.title_price_decrease)
                }
                HighlightType.NEARING_EXPIRATION.value -> {
                    stringResource(R.string.title_nearing_expiration)
                }

                else -> {"Not founded"}
            }


            Text(resourceTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer(scaleY = if (searchItem.expanded) -1f else 1f),
                painter = painterResource(R.drawable.arrowdownblackicon),
                tint = Color.Unspecified,
                contentDescription = "Arrow down"
            )
        }

        AnimatedVisibility(searchItem.expanded) {
            Column {
                searchItem.items.forEach { item ->
                    HighlightItemView(item)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

        }
    }
}


@Composable
fun AlertMessage(message: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.label_confirmation)) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium)
            }
        }
    )
}