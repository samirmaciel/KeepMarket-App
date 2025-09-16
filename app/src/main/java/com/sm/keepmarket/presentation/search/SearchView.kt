package com.sm.keepmarket.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.components.SearchItemView
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchView(paddingValues: PaddingValues) {

    val viewModel: SearchViewModel = koinViewModel()
    val searchItemList = viewModel.searchItemList.collectAsState()
    val navController = LocalNavHostController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(modifier = Modifier.padding(16.dp), onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrowlefticon),
                        tint = Color.Unspecified,
                        contentDescription = "Arrow back view"
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = stringResource(R.string.title_search),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    viewModel.setAllExpanded(false)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.collapseicon),
                        tint = Color.Black,
                        contentDescription = "Search view"
                    )
                }

                IconButton(onClick = {
                    viewModel.setAllExpanded(true)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.expandallicon),
                        tint = Color.Black,
                        contentDescription = "Search view"
                    )
                }
            }
        }



        val scroll = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(scroll)
        ) {

            if(searchItemList.value.isNotEmpty()){
                searchItemList.value.forEach { item ->
                    SearchItemView(item){ expanded ->
                        viewModel.updatedExpandedItem(item, expanded)
                    }
                }
            }

        }
    }
}
