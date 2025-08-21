package com.sm.keepmarket.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.SearchItemView
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.util.Mock

@Composable
fun SearchView(paddingValues: PaddingValues) {
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

        Text(
            modifier = Modifier.padding(16.dp),
            text = "Search",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            items(Mock.getSearchItemList()) { item ->
                SearchItemView(item)
            }

        }
    }
}