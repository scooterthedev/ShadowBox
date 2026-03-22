package com.scooter.shadowbox.UI.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.scooter.shadowbox.SettingsActivity
import com.scooter.shadowbox.UI.ui.theme.ShadowBoxTheme
import com.scooter.shadowbox.auth.login.LoginActivity
import com.scooter.shadowbox.navigation.AppNavigation
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        authCheck()
    }

    private fun main() {
        setContent {
            ShadowBoxTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    onLogout = {
                        auth.signOut()
                        authCheck()
                    },
                    onSettings = {
                        val int = Intent(this, SettingsActivity::class.java)
                        startActivity(int)
                    }
                )
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