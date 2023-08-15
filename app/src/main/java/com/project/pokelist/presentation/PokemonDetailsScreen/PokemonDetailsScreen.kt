package com.project.pokelist.presentation.PokemonDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.pokelist.data.Resource
import com.project.pokelist.data.dto.PokemonResponse.PokemonResponse
import com.project.pokelist.R
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.mappers.toPokemonEntity
import com.project.pokelist.data.mappers.toPokemonEntityi

@Composable
fun PokemonDetailsScreen(
    pokemonId: Int,
    navController: NavController,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
){
    var pokemon = produceState<Resource<PokemonResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonDetails(pokemonId)
    }.value.toPokemonEntity()
    if(pokemon.name == "durachok"){
        pokemon = produceState<Resource<PokemonEntity>>(initialValue = Resource.Loading()) {
            value = viewModel.getPokemonDetailsDb(pokemonId)
        }.value.toPokemonEntityi()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Black)
        .padding(bottom = 10.dp)
    ){
        PokemonDetailsGetBack(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 190.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 110.dp
            )
            .clip(CutCornerShape(5.dp))
            .background(White)
            .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ){
            PokemonStatsBox(pokemon = pokemon)
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        )
        {
               AsyncImage(
                    modifier = Modifier.size(200.dp),
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name

                )
        }
        

    }

}

@Composable
fun PokemonDetailsGetBack(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Box(contentAlignment = Alignment.TopCenter){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun PokemonStatsBox(
    pokemon: PokemonEntity,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .offset(y = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "${pokemon.name.toUpperCase()}",
            color = DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        PokemonTypes(pokemon = pokemon)
        PokemonStats(pokemon = pokemon)
    }
}

@Composable
fun PokemonTypes(pokemon: PokemonEntity){
  Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
          .padding(16.dp)
  ){
      for(type in pokemon.types.split(",").toList()) {
          Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                  .weight(1f)
                  .padding(10.dp, 4.dp)
                  .clip(CutCornerShape(5.dp))
                  .height(40.dp)
                  .background(Black)
          ){

              Text(
                  text = type.toUpperCase(),
                  color = White,
                  fontSize = 20.sp
              )
              
          }
      }
  }
}

@Composable
fun PokemonStats(
    pokemon: PokemonEntity
){
    val pokemonKg = remember {
        pokemon.weight*100f/100f
    }
    val pokemonM = remember {
        pokemon.height*100f/100f
    }
    Row()
    {
        PokemonStatsTemplate(value = pokemonKg,
            unit = "kg",
            icon = ImageBitmap.imageResource(R.drawable.weight))
        Spacer(modifier = Modifier
            .size(20.dp, 5.dp))
        PokemonStatsTemplate(value = pokemonM,
            unit = "m",
            icon = ImageBitmap.imageResource(R.drawable.height))}

}

@Composable
fun PokemonStatsTemplate(
    value: Float,
    unit: String,
    icon: ImageBitmap,
    modifier: Modifier = Modifier
){
    Row(
    ){
        Box(modifier = Modifier.size(40.dp)){
        Image( bitmap = icon,
            contentDescription = "icon" )}
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.size(47.dp), contentAlignment = Alignment.Center){
            Text(
            text = "$value$unit",
            color = DarkGray)}
    }

}
