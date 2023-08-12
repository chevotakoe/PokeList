package com.project.pokelist.data.dto.PokemonResponse

data class Move(
    val move: com.project.pokelist.data.dto.PokemonResponse.MoveX,
    val version_group_details: List<com.project.pokelist.data.dto.PokemonResponse.VersionGroupDetail>
)