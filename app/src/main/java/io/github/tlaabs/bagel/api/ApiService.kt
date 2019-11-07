package io.github.tlaabs.bbaaggeell.api

import io.github.tlaabs.bagel.api.GithubRepos
import io.github.tlaabs.bagel.api.GithubUser
import io.github.tlaabs.bagel.api.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/users/{username}")
    fun searchUser(
        @Path("username") username: String
    ): Call<GithubUser>

    @GET("/users/{username}/repos")
    fun searchUserRepos(
        @Path("username") username: String
    ): Call<List<Repo>>

}