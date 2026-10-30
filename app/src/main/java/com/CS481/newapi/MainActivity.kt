package com.CS481.newapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.Call
class MainActivity : AppCompatActivity() {
    //Will create an object to config the builder:
    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(APIinterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getJsonData()
    }

    private fun getJsonData() {
        val jsonData = retrofitBuilder.getAPIData()
        jsonData.enqueue(object : Callback<List<DataAPIItem>?> {
            override fun onResponse(
                call: Call<List<DataAPIItem>?>,
                response: Response<List<DataAPIItem>?>
            ) {
                val responseReturn = response.body()
                if (responseReturn != null)
                    for (data in responseReturn) {
                        Log.d("APISUCCESS", "ID:" + data.id)
                        Log.d("APISUCCESS", "Title" + data.title)
                    }
            }
            override fun onFailure(call: Call<List<DataAPIItem>?>, t: Throwable) {
                Log.d("APIFAIL", "message" + t.message)
            }
        })
    }
}