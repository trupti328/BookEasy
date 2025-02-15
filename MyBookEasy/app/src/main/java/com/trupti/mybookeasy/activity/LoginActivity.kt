package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.network.LoginRequest
import com.trupti.mybookeasy.network.LoginResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var registerTextView: TextView
    private lateinit var btnYourShopHere: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        registerTextView = findViewById(R.id.registerTextView)
        btnYourShopHere = findViewById(R.id.btnYourShopHere)

        // Handle Login
        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to Register
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Shop Owner Login
        btnYourShopHere.setOnClickListener {
            val intent = Intent(this, OwnerLoginActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to call backend login API
    private fun loginUser(email: String, password: String) {
        val request = LoginRequest(email, password)

        RetrofitClient.instance.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()


                    if (loginResponse!!.success) {
                        Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid response from server", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
