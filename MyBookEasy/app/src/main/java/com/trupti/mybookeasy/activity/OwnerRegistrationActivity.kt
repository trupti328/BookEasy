package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.network.OwnerRegisterRequest
import com.trupti.mybookeasy.network.OwnerRegisterResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class OwnerRegistrationActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMobile: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_registration)

        // Initialize Views
        btnRegister = findViewById(R.id.btnRegister)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMobile = findViewById(R.id.etMobile)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvLogin = findViewById(R.id.tvLogin)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registerOwner(name, email, mobile, password)
        }
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, OwnerLoginActivity::class.java)
        startActivity(intent)
    }

    // Function to call backend API for user registration
    private fun registerOwner(name: String, email: String, mobile: String, password: String) {
        val request = OwnerRegisterRequest(name, email, mobile, password)

        RetrofitClient.instance.registerOwner(request).enqueue(object : Callback<OwnerRegisterResponse> {
            override fun onResponse(call: Call<OwnerRegisterResponse>, response: Response<OwnerRegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null && registerResponse.success) {
                        Toast.makeText(this@OwnerRegistrationActivity, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@OwnerRegistrationActivity, OwnerLoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@OwnerRegistrationActivity, registerResponse?.message ?: "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Log error details for debugging
                    Log.e("RegisterError", "Response Code: ${response.code()}, Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@OwnerRegistrationActivity, "Server Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OwnerRegisterResponse>, t: Throwable) {
                Log.e("RegisterFailure", "API Call Failed", t)
                Toast.makeText(this@OwnerRegistrationActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
