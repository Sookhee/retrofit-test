package kr.h.emirim.sookhee.sfoid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserEndpoints {

    @GET("/api")
    fun getUsers(@Query("results") key: Int): Call<Users>

}