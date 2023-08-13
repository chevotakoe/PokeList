package com.project.pokelist.data.dto.PokemonResponse

data class Move(
    val move:MoveX,
    val version_group_details: List<VersionGroupDetail>
)