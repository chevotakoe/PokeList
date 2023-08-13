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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.pokelist.domain.Pokemon
import com.project.pokelist.ui.theme.PokeListTheme
import com.project.pokelist.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
){
    ElevatedCard(
        modifier = modifier.fillMaxWidth().padding(18.dp, 6.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ){
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(color = Purple40)
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center),
                contentAlignment = Alignment.Center
            ){
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = pokemon.imageUrl,
                contentDescription = pokemon.name
            )}
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Cyan
                            ),
                            startY = 112.5f
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
                        color = Color.DarkGray,
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

