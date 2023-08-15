package com.project.pokelist.presentation.PokemonDetailsScreen

import androidx.lifecycle.ViewModel
import com.project.pokelist.data.Resource
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonDetails(pokemonId: Int): Resource<PokemonResponse> {
        return repository.getPokemonEntity(pokemonId)
    }

    suspend fun getPokemonDetailsDb(pokemonId: Int): Resource<PokemonEntity> {
        return repository.getPokemonEntityDb(pokemonId)
    }
}