package com.scooter.shadowbox.UI.home

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scooter.shadowbox.UI.jetpack.BottomBar
import com.scooter.shadowbox.UI.jetpack.NavDrawer
import com.scooter.shadowbox.data.SSHConnection
import kotlinx.coroutines.launch
import net.schmizz.sshj.connection.Connection

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(
    onAddConnection: () -> Unit,
    onEditConnection: (String) -> Unit,
    onLogout: () -> Unit,
    onSettings: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val connections = remember { mutableStateOf<List<SSHConnection>>(emptyList()) }

    NavDrawer(
        drawerState = drawerState,
        onItemSelected = { itemId ->
            scope.launch { drawerState.close() }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("ShadowBox")},
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() }}) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddConnection) {
                    Icon(Icons.Default.Add, contentDescription = "Add Connection")
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                connections.forEach { connection ->
                    ConnectionItem(
                        connection = connectionm
                        onEdit = { onEditConnection(connection.id) }
                    ) { }
                }
            }
        }
    }
}

@Composable
fun ConnectionItem(connection: SSHConnection, onEdit: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = connection.name, style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    }
}