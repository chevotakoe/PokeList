package com.project.pokelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.pokelist.presentation.PokemonListScreen
import com.project.pokelist.presentation.PokemonViewModel
import com.project.pokelist.ui.theme.PokeListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<PokemonViewModel>()
                    val pokemons = viewModel.PokemonPagingFlow.collectAsLazyPagingItems()
                    PokemonListScreen(pokemons = pokemons)

                }
            }
        }
    }
}
