package com.project.pokelist.data.dto.PokemonResponse

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: com.project.pokelist.data.dto.PokemonResponse.StatX
)