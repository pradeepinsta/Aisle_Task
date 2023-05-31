package com.pradeepuct.aisletask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class NotesScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_screen)

        fun makeNotesAPICall(authToken: String) {
            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()
                    .header("Authorization", authToken)
                    .build()
                chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://app.aisle.co")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.getNotes().also {

                it.enqueue{
                    fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {
                        if (response.isSuccessful) {
                            val noteResponse = response.body()
                            if (noteResponse != null) {
                                val notes = noteResponse.notes
                                // Handle the notes
                            } else {
                                // Handle null response case
                            }
                        } else {
                            // Handle error case
                        }
                    }

                    fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                        // Handle failure case
                    }
                }
            }
        }
    }
}