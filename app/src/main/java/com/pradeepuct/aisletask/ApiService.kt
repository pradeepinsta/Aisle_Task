package com.pradeepuct.aisletask

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class PhoneNumberRequest(val number: String)
data class OtpRequest(val number: String, val otp: String)
data class NoteResponse(val notes: List<String>)

interface ApiService {
    @POST("/users/phone_number_login")
    fun phoneNumberLogin(@Body request: PhoneNumberRequest): Call<Unit>

    @POST("/users/verify_otp")
    fun verifyOtp(@Body request: OtpRequest): Call<AuthResponse>

    @GET("/users/test_profile_list")
    fun getNotes(): Call<NoteResponse>
}
