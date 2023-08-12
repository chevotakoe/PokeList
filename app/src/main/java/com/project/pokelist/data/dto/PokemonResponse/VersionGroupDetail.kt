package com.project.pokelist.data.dto.PokemonResponse

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.project.pokelist.data.dto.PokemonResponse.MoveLearnMethod,
    val version_group: com.project.pokelist.data.dto.PokemonResponse.VersionGroup
)