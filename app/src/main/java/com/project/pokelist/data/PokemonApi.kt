package com.project.pokelist.data

import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.data.dto.PokemonListResponse.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonEntity(
        @Path("id") id: Int
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonEntityByName(
        @Path("name") name: String
    ): PokemonResponse

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}