package com.trupti.mybookeasy.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.trupti.mybookeasy.R

class ShopRegisttrationActivity : AppCompatActivity() {

    private lateinit var shopImageView: ImageView
    private lateinit var btnUploadImage: Button
    private val REQUEST_GALLERY = 1
    private val REQUEST_CAMERA = 2
    private val PERMISSION_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_registtration)

                shopImageView = findViewById(R.id.shopImageView)
                btnUploadImage = findViewById(R.id.btnUploadImage)

                btnUploadImage.setOnClickListener {
                    requestPermissions()
                }
    }
    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                openImagePicker()
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA), PERMISSION_CODE)
            }
        } else {
            openImagePicker()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                openImagePicker()
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openImagePicker() {
        val options = arrayOf("Choose from Gallery", "Capture from Camera")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Select Image")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_GALLERY)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_GALLERY -> {
                    val selectedImageUri: Uri? = data?.data
                    shopImageView.setImageURI(selectedImageUri)
                }
                REQUEST_CAMERA -> {
                    val photo: Bitmap = data?.extras?.get("data") as Bitmap
                    shopImageView.setImageBitmap(photo)
                }
            }
        }
    }
}