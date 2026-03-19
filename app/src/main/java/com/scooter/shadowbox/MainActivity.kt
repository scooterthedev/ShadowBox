package com.scooter.shadowbox

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.scooter.shadowbox.UI.NavDrawer
import com.scooter.shadowbox.UI.ui.theme.ShadowBoxTheme
import com.scooter.shadowbox.auth.login.LoginActivity
import kotlinx.coroutines.launch
import com.scooter.shadowbox.UI.BottomBar

class MainActivity : AppCompatActivity() {

    // Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        authCheck()
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
    private fun main() {
        setContent {
            ShadowBoxTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                NavDrawer(
                    drawerState = drawerState,
                    onItemSelected = { itemId ->
                        when (itemId) {
                            R.id.nav_logout -> {
                                auth.signOut()
                                authCheck()
                            }
                            R.id.nav_settings -> {
                                // TODO
                            }
                            R.id.nav_account -> {
                                // TODO
                            }
                            R.id.nav_sftp -> {
                                // TODO
                            }
                            R.id.nav_ftp -> {
                                // TODO
                            }
                            R.id.nav_ssh -> {
                                // TODO
                            }
                        }
                        scope.launch { drawerState.close() }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("ShadowBox") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch { drawerState.open() }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            BottomBar()
                        }
                    ) { innerPadding ->

                        Text(
                            text = "Main Content Area",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    private fun authCheck() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            main()
        } else {
            val intLogin = Intent(this, LoginActivity::class.java)
            startActivity(intLogin)
            finish()
        }
    }
}
