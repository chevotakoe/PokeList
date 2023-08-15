package com.project.pokelist.domain

import com.project.pokelist.data.PokemonApi
import com.project.pokelist.data.Resource
import com.project.pokelist.data.db.PokemonDatabase
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private val api: PokemonApi,
    private val db: PokemonDatabase
) {

    suspend fun getPokemonEntity(pokemonId: Int): Resource<PokemonResponse> {
        val response = try {
            api.getPokemonEntity(pokemonId)
        } catch(e: Exception) {
            return Resource.Error("Error occurred.")
        }
        return Resource.Success(response)
    }

    fun getPokemonEntityDb(pokemonId: Int): Resource<PokemonEntity> {
        val response = try {
            db.dao.pagingSourceId(pokemonId)
        } catch(e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }


}
