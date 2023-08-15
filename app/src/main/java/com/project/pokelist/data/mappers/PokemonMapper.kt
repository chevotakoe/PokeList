package com.project.pokelist.data.mappers

import com.project.pokelist.data.Resource
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.domain.models.Pokemon

fun Resource<PokemonResponse>.toPokemonEntity(): PokemonEntity {
    if(data!=null) {
        var typeList = mutableListOf<String>()
        data.types.forEach {
            typeList.add(it.type.name)
        }
        val typesString = typeList.joinToString(separator = ",")

        return data.let {
            PokemonEntity(
                id = it.id,
                name = it.name,
                types = typesString,
                height = it.height,
                weight = it.weight,
                imageUrl = it.sprites.front_default
            )
        }

    }
    else{
        return PokemonEntity(
            id = 1,
            name = "durachok",
            types = "sdsdfsdfsdf",
            height = 12,
            weight = 2,
            imageUrl = "adadadawdw"
        )
    }
}

fun Resource<PokemonEntity>.toPokemonEntityi(): PokemonEntity {
    if(data!=null) {
        return data.let {
            PokemonEntity(
                id = it.id,
                name = it.name,
                types = it.types,
                height = it.height,
                weight = it.weight,
                imageUrl = it.imageUrl
            )
        }

    }
    else{
        return PokemonEntity(
            id = 1,
            name = "durachok",
            types = "sdsdfsdfsdf",
            height = 12,
            weight = 2,
            imageUrl = "adadadawdw"
        )
    }
}

fun PokemonResponse.toPokemonEntity(): PokemonEntity{
    var typeList = mutableListOf<String>()
    types.forEach{typeList.add(it.type.name)
    }
    val typesString = typeList.joinToString(separator = ",")
    return PokemonEntity(
        id = id,
        name = name,
        types = typesString,
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

