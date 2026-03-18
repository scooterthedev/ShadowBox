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

    private lateinit var binding: ActivityMainBinding

    // Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setSupportActionBar(binding.toolbar)

        authCheck()
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

    private fun main() {

    }
}