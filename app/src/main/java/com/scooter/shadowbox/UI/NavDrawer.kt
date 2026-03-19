package com.scooter.shadowbox.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.scooter.shadowbox.R

@Composable
fun NavDrawer(
    drawerState: DrawerState,
    onItemSelected: (Int) -> Unit = {},
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "ShadowBox",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
                NavigationDrawerItem(
                    label = { Text("SSH", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_ssh) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("FTP", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_ftp) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("SFTP", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_sftp) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("My Account", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_account) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("Settings", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_settings) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("Logout", modifier = Modifier.padding(top = 30.dp)) },
                    selected = false,
                    onClick = { onItemSelected(R.id.nav_logout) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Version 0.0.1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                    )
            }
        },
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun NavDrawerOpenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    NavDrawer(drawerState = drawerState) {
    }
}

@Preview(showBackground = true)
@Composable
fun NavDrawerClosedPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    NavDrawer(drawerState = drawerState) {
        Column(modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)) {
            Text(text = "Main Content Area")
        }
    }
}
