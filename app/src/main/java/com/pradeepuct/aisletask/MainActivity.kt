package com.pradeepuct.aisletask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.aisle.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val buttonContinue = findViewById<Button>(R.id.buttonContinue)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhonenumber)

        buttonContinue.setOnClickListener()
        {
            val phoneNumber = "+91" + editTextPhone.text.toString()

            val TAG = "MainActivity"
            Log.d(TAG, "onCreate: Phone number$phoneNumber")

            // Make the Phone number API call
            val phoneNumberRequest = PhoneNumberRequest(number = phoneNumber)
            val call = apiService.phoneNumberLogin(phoneNumberRequest)

            call.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        // Phone number API call success
                        startActivity(Intent(this@MainActivity,OTPScreen::class.java))
                    } else {
                        // Handle error case

                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    // Handle failure case

                }
            })

        }

    }
}