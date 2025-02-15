package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.network.OwnerLoginRequest
import com.trupti.mybookeasy.network.OwnerLoginResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class OwnerLoginActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var registerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_login)

        // Initialize Views
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        registerTextView = findViewById(R.id.registerTextView)

        // Handle Login
        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginOwner(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to Register
        registerTextView.setOnClickListener {
            val intent = Intent(this, OwnerRegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to call backend login API
    private fun loginOwner(email: String, password: String) {
        val request = OwnerLoginRequest(email, password)

        Log.d("LoginOwner", "Attempting login with email: $email")

        RetrofitClient.instance.loginOwner(request).enqueue(object : Callback<OwnerLoginResponse> {
            override fun onResponse(call: Call<OwnerLoginResponse>, response: Response<OwnerLoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()

                    if (loginResponse!!.success) {
                        Toast.makeText(this@OwnerLoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@OwnerLoginActivity, ShopDashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("LoginError", "Login failed: ${loginResponse.message}")
                        Toast.makeText(this@OwnerLoginActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("LoginError", "Invalid response from server: ${response.errorBody()?.string()}")
                    Toast.makeText(this@OwnerLoginActivity, "Invalid response from server", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OwnerLoginResponse>, t: Throwable) {
                Log.e("LoginFailure", "API Call Failed", t)
                Toast.makeText(this@OwnerLoginActivity, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
