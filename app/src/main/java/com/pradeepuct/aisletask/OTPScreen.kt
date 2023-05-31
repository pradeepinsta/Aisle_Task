package com.pradeepuct.aisletask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback
import com.pradeepuct.aisletask.AuthResponse as AuthResponse1

internal fun <T> Call<T>.enqueue(any: Any) {
    TODO("Not yet implemented")
}

class OTPScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpscreen)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.aisle.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val buttonContinue = findViewById<Button>(R.id.buttonOtpContinue)
        val editTextOtp = findViewById<EditText>(R.id.editTextOtp)

        val apiService = retrofit.create(ApiService::class.java)

        buttonContinue.setOnClickListener()
        {
            val otp = editTextOtp.text.toString()

            // Make the OTP API call
            val otpRequest = OtpRequest(number = "+919876543212", otp = otp)
            val call = apiService.verifyOtp(otpRequest).also {

                it.enqueue{
                   fun onResponse(call: Call<AuthResponse1>, response: Response<AuthResponse1>) {
                        if (response.isSuccessful) {
                            val authResponse = response.body()
                            if (authResponse != null) {
                                val authToken = authResponse.authToken
                                // OTP API call success
                                startActivity(Intent(this@OTPScreen,NotesScreen::class.java)
                                    .putExtra(authToken))
                            } else {
                                // Handle null response case
                                val toast = Toast.makeText(applicationContext, "The field was empty",Toast.LENGTH_LONG)
                                toast.show()
                            }
                        } else {
                            // Handle error case
                            val toast = Toast.makeText(applicationContext,"Error...",Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }

                     fun onFailure(call: Call<AuthResponse1>, t: Throwable) {
                        // Handle failure case
                         val toast = Toast.makeText(applicationContext, "Error...$t",Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }
}

private fun Intent.putExtra(authToken: String): Intent? {
    TODO("Not yet implemented")
}

