package com.sm.keepmarket.presentation.pantryList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.UiStateView
import org.koin.androidx.compose.koinViewModel

@Composable
fun PantryListSelectionView(paddingValues: PaddingValues) {

    val viewModel: PantryListSelectionViewModel = koinViewModel()
    var showCreatePantryList by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val navController = LocalNavHostController.current
    val context = LocalContext.current

    LaunchedEffect(context){
        viewModel.getPantryList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    text = "Pantry List",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = { showCreatePantryList = true },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text(
                    "Create Pantry List",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp
                )
            }
        }

        if (uiState is UiStateView.Success) {
            LazyColumn(modifier =  Modifier.fillMaxWidth()) {
                items((uiState as UiStateView.Success<List<Pantry>>).data) { item ->
                    PantryListSelectionItemView(
                        item, onDelete = { pantry ->
                            viewModel.deletePantry(pantry)
                        },
                        onEdit = { pantry ->
                            viewModel.updatePantry(pantry)
                        },
                        onNavigate = {
                            navController.navigate(Dest.PantryListView(item.id))
                        })
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
        }

    }

    if (showCreatePantryList) {
        CreateNewListModal(
            hint = "Pantry list name",
            onDismiss = { showCreatePantryList = false },
            onFinish = { pantryListName ->
                viewModel.createPantry(pantryListName)
                showCreatePantryList = false
            })
    }
}

@Composable
fun PantryListSelectionItemView(
    pantry: Pantry,
    onDelete: (Pantry) -> Unit,
    onEdit: (Pantry) -> Unit,
    onNavigate: () -> Unit
) {

    var showEditItemModal by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var expandedMoreActions by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(color = Blue, shape = RoundedCornerShape(10.dp))
            .clickable(enabled = true){
                onNavigate()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                pantry.name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = pantry.getFormattedLastUpdate(),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp,
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
            text = "● ${pantry.items.size}",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
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
            title = { Text("Confirmação") },
            text = { Text("Você deseja realmente excluir esta lista?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(pantry)
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text("Sim", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text("Não", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }

    if (showEditItemModal) {
        PantryNameEdit(pantry, onDismiss = { showEditItemModal = false }) { newPantry ->
            onEdit(newPantry)
        }
    }
}

@Composable
fun PantryNameEdit(pantry: Pantry, onDismiss: () -> Unit, onFinish: (Pantry) -> Unit) {

    var name by remember { mutableStateOf(pantry.name) }
    var showNameErrorMessage by remember { mutableStateOf(false) }


    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = {

                    if (it.isNotEmpty()) {
                        showNameErrorMessage = false
                    }

                    name = it
                },
                label = { Text("Pantry name") },
                modifier = Modifier.fillMaxWidth(),
            )
            if (showNameErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Field should be not empty",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = Red
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp), horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {

                        if (name.isEmpty()) {
                            showNameErrorMessage = true
                            return@Button
                        }

                        val newPantry = pantry.copy(
                            name = name
                        )

                        onFinish(newPantry)
                        onDismiss()

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Save", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}