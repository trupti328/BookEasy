package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.network.RegisterRequest
import com.trupti.mybookeasy.network.RegisterResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMobile: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btnRegister)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMobile = findViewById(R.id.etMobile)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvLogin = findViewById(R.id.tvLogin)

        // Handle register button click
        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Send registration data to backend
                registerUser(name, email, mobile, password)
            }
        }

        // Navigate to login screen
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to call backend API for user registration
    private fun registerUser(name: String, email: String, mobile: String, password: String) {
        val request = RegisterRequest(name, email, mobile, password)

        RetrofitClient.instance.registerUser(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val registerResponse = response.body()
                    if (registerResponse!!.success) {
                        Toast.makeText(this@RegisterActivity, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, registerResponse.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Invalid response from server", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Registration Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
