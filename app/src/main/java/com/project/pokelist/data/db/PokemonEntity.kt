package com.project.pokelist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val types: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String
)
