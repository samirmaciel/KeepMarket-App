package com.sm.keepmarket.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.BottomMenu
import com.sm.keepmarket.components.MiddleItemHighlights
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.KeepMarketTheme

@Composable
fun HomeView() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Background,
        bottomBar = { BottomMenu() }) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(R.drawable.settingsicon), contentDescription = "Settings button")
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalAlignment = Alignment.Start) {
                Text("Hello User!", style = MaterialTheme.typography.titleLarge)
                Text("Have a nice day.", style = MaterialTheme.typography.labelSmall)
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier.padding(start = 10.dp), onClick = {},
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )) {
                        Text("Prices", color = Color.Black, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                    }
                Button(modifier = Modifier.padding(start = 10.dp),onClick = {},
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )) {
                    Text("Lost items", color = Color.Black, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                }
                Button(modifier = Modifier.padding(start = 10.dp),onClick = {},
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )) {
                    Text("Most used", color = Color.Black, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                }
            }

            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                MiddleItemHighlights()
                MiddleItemHighlights()
            }

            Text(modifier = Modifier.padding(16.dp), text = "Highlights", style = MaterialTheme.typography.labelMedium)

            LazyColumn() {

            }
        }
    }
}


@Composable
@Preview
fun Preview(modifier: Modifier = Modifier) {

    KeepMarketTheme {
        HomeView()
    }
}