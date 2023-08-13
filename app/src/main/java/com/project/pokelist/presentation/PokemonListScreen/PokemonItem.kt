package com.project.pokelist.presentation.PokemonListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.pokelist.domain.Pokemon
import com.project.pokelist.ui.theme.PokeListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
){
    ElevatedCard(
        modifier = modifier.fillMaxWidth().padding(18.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ){
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(color = Green)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                alignment = Alignment.Center
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Cyan
                            ),
                            startY = 150f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    pokemon.name.toUpperCase(),
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }


    }
}



@Preview
@Composable
fun PokemonItemPreview() {
    PokeListTheme {
        PokemonItem(pokemon = Pokemon(
            id = 1,
            name = "Nehochu",
            types = "grass,water",
            weight = 3,
            height = 4,
            imageUrl = "ssdfsefefwef"
        ),
            modifier = Modifier.fillMaxWidth()
        )

    }
}

