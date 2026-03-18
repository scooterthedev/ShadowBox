package com.scooter.shadowbox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.scooter.shadowbox.databinding.ActivityMainBinding
import com.google.firebase.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.scooter.shadowbox.auth.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setSupportActionBar(binding.toolbar)

        auth_check()
    }

    private fun auth_check() {
        val currentUser = auth.currentUser
        if (currentUser != null) {

        } else {
            val int_login = Intent(this, LoginActivity::class.java)
            startActivity(int_login)
            finish()
        }
    }
}