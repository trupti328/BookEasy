package com.trupti.mybookeasy.network


import com.trupti.mybookeasy.entity.Category
import com.trupti.mybookeasy.entity.CategoryResponse
import com.trupti.mybookeasy.entity.Shop
import com.trupti.mybookeasy.entity.ShopResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//costomer
//Login
//data class for request
data class LoginRequest(
    val email: String,
    val password : String
)

//data class for response
data class LoginResponse(
    val success: Boolean,
    val message: String
)


//Register
data class RegisterRequest(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)
data class RegisterResponse(
    val success: Boolean,
    val message: String
)
//owner
//Login
//data class for request
data class OwnerLoginRequest(
    val email: String,
    val password : String
)

//data class for response
data class OwnerLoginResponse(
    val success: Boolean,
    val message: String
)

//Register
data class OwnerRegisterRequest(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)
data class OwnerRegisterResponse(
    val success: Boolean,
    val message: String
)

//API
interface Api {
    @POST("/customer/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/customer/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>




    @POST("/owner/login")
    fun loginOwner(@Body request: OwnerLoginRequest): Call<OwnerLoginResponse>

    @POST("/owner/register")
    fun registerOwner(@Body request: OwnerRegisterRequest): Call<OwnerRegisterResponse>




    @GET("/category/categories")
    fun getCategories():Call<CategoryResponse>

    @GET("/shop/category/{category_id}")
    fun getShopsByCategory(@Path("category_id") categoryId: Int): Call<List<Shop>>

}