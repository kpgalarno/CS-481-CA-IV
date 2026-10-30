package com.CS481.newapi

import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("posts")    //Uses the http get method?
    fun getAPIData(): Call<List<DataAPIItem>>   //Call is provided by retrofit
}