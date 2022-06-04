package com.example.formapp.data.source

import com.example.formapp.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface FormApi {

    @POST("user")
    suspend fun insertUser(@Body user: User)
}