package com.sm.keepmarket.presentation.search

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.SearchItemView
import com.sm.keepmarket.presentation.theme.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchView(paddingValues: PaddingValues) {

    val viewModel: SearchViewModel = koinViewModel()
    val searchItemList = viewModel.searchItemList.collectAsState()
    var expandAll by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(paddingValues)
    ) {

        IconButton(modifier = Modifier.padding(16.dp), onClick = {

        }) {
            Icon(
                painter = painterResource(R.drawable.arrowlefticon),
                tint = Color.Unspecified,
                contentDescription = "Arrow back view"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Search",
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(onClick = {
                expandAll = false
            }) {
                Icon(
                    painter = painterResource(R.drawable.collapseicon),
                    tint = Color.Black,
                    contentDescription = "Search view"
                )
            }

            IconButton(onClick = {
                expandAll = true
            }) {
                Icon(
                    painter = painterResource(R.drawable.expandallicon),
                    tint = Color.Black,
                    contentDescription = "Search view"
                )
            }
        }

        val scroll = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(scroll)
        ) {
            searchItemList.value.forEach { item ->
                SearchItemView(item, expandAll)
            }
        }
    }
}
