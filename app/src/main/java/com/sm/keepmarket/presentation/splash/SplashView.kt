package com.sm.keepmarket.presentation.splash

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.MainNavigation
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.jetbrains.annotations.TestOnly
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashView() {
    val viewModel: SplashViewModel = koinViewModel()
    val listState by viewModel.listState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.hasCreatedList()
    }

    when (listState) {
        is UiStateView.Error -> {
        }

        is UiStateView.Loading -> {
            LoadingView()
        }

        is UiStateView.Success<*> -> {
            if((listState as UiStateView.Success<*>).data == true){
                MainNavigation()
            }else{
                CreateView(viewModel)
            }
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CreateView(viewModel: SplashViewModel) {

    var showCreateMarketList by remember { mutableStateOf(false) }
    var showCreatePantryList by remember { mutableStateOf(false) }
    val userName = viewModel.userName.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello $userName!", style = MaterialTheme.typography.titleLarge)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    showCreateMarketList = true
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Create Market List")
            }
            Button(
                onClick = {
                    showCreatePantryList = true
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Create Pantry List")
            }
        }
    }

    if (showCreateMarketList) {
        CreateNewListModal(
            hint = "Market list name",
            onDismiss = { showCreateMarketList = false },
            onFinish = { marketListName ->
                viewModel.createMarketList(marketListName)
                showCreateMarketList = false
            })
    }

    if (showCreatePantryList) {
        CreateNewListModal(
            hint = "Pantry list name",
            onDismiss = { showCreatePantryList = false },
            onFinish = { pantryListName ->
                viewModel.createPantryList(pantryListName)
                showCreatePantryList = false
            })
    }
}