package com.project.pokelist.presentation.PokemonListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.pokelist.domain.models.Pokemon
import com.project.pokelist.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    navController: NavController
){
    ElevatedCard(
        modifier = modifier.fillMaxWidth().padding(18.dp, 6.dp).clickable {
            navController.navigate(
            "pokemon_detail_screen/${pokemon.id}"
        ) },
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


