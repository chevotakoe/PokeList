package com.project.pokelist.data.dto.PokemonListResponse

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)