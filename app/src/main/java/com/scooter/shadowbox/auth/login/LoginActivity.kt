package com.scooter.shadowbox.auth.login

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import com.scooter.shadowbox.MainActivity
import com.scooter.shadowbox.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val provider = OAuthProvider.newBuilder("github.com").apply {
        scopes = listOf("user:email")
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        login()
        githubLogin()
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun login() {
        binding.passLogin.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            reload()
                        } else {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this) { registerTask ->
                                    if (registerTask.isSuccessful) {
                                        reload()
                                    } else {
                                        Toast.makeText(
                                            baseContext,
                                            "Auth Failed: ${registerTask.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    }
            } else {
                Toast.makeText(baseContext, "Please Enter Your Email and Password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun githubLogin() {
        val pendingResultTask = auth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener {
                    reload()
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext, "Failed to Login via Github", Toast.LENGTH_SHORT).show()
                }
            } else {
            binding.githubLogin.setOnClickListener {
                auth.startActivityForSignInWithProvider(this, provider.build())
                    .addOnSuccessListener { authResult ->
                        reload()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(baseContext, "Failed to login via Github: ${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }


    private fun reload() {
        val int = Intent(this, MainActivity::class.java)
        startActivity(int)
        finish()
    }
}