package com.sm.keepmarket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.ButtonDefault

@Composable
fun BottomMenu() {
    var selectedIndex by remember { mutableStateOf(0) }

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Background
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            IconButton(modifier = Modifier.size(50.dp), onClick = { selectedIndex = 0 }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.homeicon),
                    tint = if (selectedIndex == 0) Blue else ButtonDefault,
                    contentDescription = "Home button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = { selectedIndex = 1 }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.calendaricon),
                    tint = if (selectedIndex == 1) Blue else ButtonDefault,
                    contentDescription = "Pantry list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = { selectedIndex = 2 }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.marketlisticon),
                    tint = if (selectedIndex == 2) Blue else ButtonDefault,
                    contentDescription = "Market list button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = { selectedIndex = 3 }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.notificationicon),
                    tint = if (selectedIndex == 3) Blue else ButtonDefault,
                    contentDescription = "Notifications button"
                )
            }

            IconButton(modifier = Modifier.size(50.dp), onClick = { selectedIndex = 4 }) {
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
fun MiddleItemHighlights() {

    Box(modifier = Modifier.padding(5.dp)){
        Column(modifier = Modifier.size(width = 150.dp, height = 150.dp).background(Blue, RoundedCornerShape(10.dp)).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(modifier = Modifier.size(30.dp).padding(end = 10.dp), painter = painterResource(R.drawable.settingsicon), tint = Color.White, contentDescription = "")
                Text("My pantry 1", style = MaterialTheme.typography.labelSmall, color = Color.White, fontSize = 10.sp)
            }
            Text(modifier = Modifier.padding(top = 20.dp), text ="Pantry", style = MaterialTheme.typography.labelMedium, color = Color.White)
            Text(modifier = Modifier.padding(top = 20.dp), text = "October 20, 2020", style = MaterialTheme.typography.labelSmall, color = Color.White, fontSize = 8.sp)
        }
    }
}