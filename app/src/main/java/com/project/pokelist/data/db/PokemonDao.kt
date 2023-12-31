package com.project.pokelist.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemonentity")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemonentity WHERE id = :pokemonId")
    fun pagingSourceId(pokemonId: Int): PokemonEntity

    @Query("DELETE FROM pokemonentity")
    suspend fun clearAll()
}