package com.project.pokelist.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemonenetity")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemonenetity")
    suspend fun clearAll()
}