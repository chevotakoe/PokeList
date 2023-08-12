package com.project.pokelist.data.dto.PokemonResponse

data class Ability(
    val ability: com.project.pokelist.data.dto.PokemonResponse.AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)