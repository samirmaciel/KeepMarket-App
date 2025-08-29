package com.sm.keepmarket.presentation.home

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.components.FeaturedCardButton
import com.sm.keepmarket.components.HighlightItemView
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.ButtonDefault
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(paddingValues: PaddingValues, isVisibleBottomMenu: (Boolean) -> Unit) {

    val nav = LocalNavHostController.current
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.state == UiState.LOADING) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (uiState.featuredCardList.isEmpty()) {
        isVisibleBottomMenu(false)
        EmptyHomeView(
            userName = "User",
            onCreateMarketList = { viewModel.createMarketList(it) },
            onCreatePantryList = { viewModel.createPantryList(it) })
    }

    if (uiState.featuredCardList.isNotEmpty()) {

        isVisibleBottomMenu(true)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalAlignment = Alignment.Start
            ) {
                Text("Hello User!", style = MaterialTheme.typography.titleLarge)
                Text("Have a nice day.", style = MaterialTheme.typography.labelSmall)
            }

            CreateList(onCreateMarket = { marketName ->
                viewModel.createMarketList(marketName)
            }, onCreatePantry = { pantryName ->
                viewModel.createPantryList(pantryName)
            })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                LazyRow {
                    items(uiState.featuredCardList) { featuredCard ->
                        FeaturedCardButton(featuredCard = featuredCard) { featuredCard ->
                            when (featuredCard.featuredType) {
                                FeaturedType.MARKET -> nav.navigate(Dest.MarketListView)
                                FeaturedType.PANTRY -> nav.navigate(Dest.PantryListView)
                            }
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }

            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Highlights",
                style = MaterialTheme.typography.labelMedium
            )

            if (uiState.highlightList.isEmpty()) {

                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        modifier = Modifier.size(300.dp),
                        painter = painterResource(R.drawable.flagicon), tint = ButtonDefault,
                        contentDescription = "Flag icon"
                    )
                }
                return
            }

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(uiState.highlightList) { hightlight ->
                    HighlightItemView(hightlight)
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
        }
    }
}


@Composable
fun CreateList(onCreateMarket: (String) -> Unit, onCreatePantry: (String) -> Unit) {

    var showCreateMarketList by remember { mutableStateOf(false) }
    var showCreatePantryList by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            onClick = { showCreateMarketList = true },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                "Create Market List",
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            modifier = Modifier
                .weight(1f), onClick = {
                showCreatePantryList = true
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                "Create Pantry List",
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
            )
        }
    }

    if (showCreateMarketList) {
        CreateNewListModal(
            hint = "Market list name",
            onDismiss = { showCreateMarketList = false },
            onFinish = { marketListName ->
                onCreateMarket(marketListName)
                showCreateMarketList = false
            })
    }

    if (showCreatePantryList) {
        CreateNewListModal(
            hint = "Pantry list name",
            onDismiss = { showCreatePantryList = false },
            onFinish = { pantryListName ->
                onCreatePantry(pantryListName)
                showCreatePantryList = false
            })
    }

}