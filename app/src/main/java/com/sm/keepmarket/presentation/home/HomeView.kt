package com.sm.keepmarket.presentation.home

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.components.FeaturedCardButton
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.Dest.*
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.UiStateView
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(paddingValues: PaddingValues) {

    val nav = LocalNavHostController.current
    val viewModel: HomeViewModel = koinViewModel()
    val featureCardListState by viewModel.featuredCardListState.collectAsState()
    val highlightListState by viewModel.highlightListState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.getAllFeaturedCardList()
        viewModel.getAllHighlight()
    }

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
            Text(stringResource(R.string.message_greeting, "User"), style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.message_nice_day), style = MaterialTheme.typography.labelSmall)
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

            when(featureCardListState){
                is UiStateView.Error -> {}
                is UiStateView.Loading -> {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator()
                    }
                }
                is UiStateView.Success<*> -> {

                    val data = (featureCardListState as UiStateView.Success).data

                    LazyRow {
                        items(data) { featuredCard ->
                            FeaturedCardButton(featuredCard = featuredCard) { featuredCard ->
                                when (featuredCard.featuredType) {
                                    FeaturedType.MARKET -> nav.navigate(MarketListView(featuredCard.id))
                                    FeaturedType.PANTRY -> nav.navigate(PantryListView(featuredCard.id))
                                }
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    }
                }

                UiStateView.Idle -> TODO()
            }
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.title_highlights),
            style = MaterialTheme.typography.labelMedium,
            fontSize = 20.sp
        )

        when(highlightListState){

            is UiStateView.Error -> {
                LaunchedEffect((highlightListState as UiStateView.Error).message) {
                    Toast.makeText(context, (highlightListState as UiStateView.Error).message, Toast.LENGTH_SHORT).show()
                }
                EmptyHighlightList()
            }

            is UiStateView.Loading -> {

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }

            }

            is UiStateView.Success<*> -> {

                val highlightList = (highlightListState as UiStateView.Success).data

                if(highlightList.isEmpty()){
                    EmptyHighlightList()
                }else{
                    HighlightList(highlightList)
                }
            }

            UiStateView.Idle -> TODO()
        }
    }
}
