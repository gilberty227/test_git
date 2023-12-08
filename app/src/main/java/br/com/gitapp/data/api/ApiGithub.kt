package br.com.gitapp.data.api

import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGithub {

    @GET("users/{user}")
    suspend fun getInfoUser(@Path("user") user: String): Response<User>

    @GET("users")
    suspend fun getListUser(): Response<List<User>>

    @GET("users/{user}/repos")
    suspend fun getListRepository(@Path("user") user: String): Response<MutableList<Repository>>
}