package com.project.pokelist.data.mappers

import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonListResponse.PokemonListResponse
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.domain.Pokemon

fun PokemonResponse.toPokemonEntity(): PokemonEntity{
    var typeList = mutableListOf<String>()
    types.forEach{typeList.add(it.type.name)}
    return PokemonEntity(
        id = id,
        name = name,
        types = typeList,
        height = height,
        weight = weight,
        imageUrl = sprites.front_default
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        types = types,
        height = height,
        weight = weight,
        imageUrl = imageUrl
    )

}