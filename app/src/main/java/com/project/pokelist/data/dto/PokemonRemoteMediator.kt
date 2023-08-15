package com.project.pokelist.data.dto

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.project.pokelist.data.PokemonApi
import com.project.pokelist.data.db.PokemonDatabase
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.data.mappers.toPokemonEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDb: PokemonDatabase,
    private val pokemonApi: PokemonApi
): RemoteMediator<Int, PokemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try{
             val loadKey = when(loadType) {
                 LoadType.REFRESH -> 1
                 LoadType.PREPEND -> return MediatorResult.Success(
                     endOfPaginationReached = true
                 )
                 LoadType.APPEND -> {
                     val lastItem = state.lastItemOrNull()
                     if(lastItem == null) {
                         1
                     } else {
                         (lastItem.id / state.config.pageSize) + 1

                     }
                 }
             }
            delay(3000L)
            val pokemons = pokemonApi.getPokemonList(
                limit = state.config.pageSize,
                offset = (loadKey-1)*20
            )

            pokemonDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()
                }
                val pokemonEntitiesList = mutableListOf<PokemonResponse>()
                pokemons.results.forEach(){
                    pokemonEntitiesList.add(pokemonApi.getPokemonEntityByName(it.name))
                }
                val pokemonEntities = pokemonEntitiesList.map{it.toPokemonEntity()}
                pokemonDb.dao.upsertAll(pokemonEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = pokemons.results.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e:HttpException) {
            MediatorResult.Error(e)
        }
    }

}