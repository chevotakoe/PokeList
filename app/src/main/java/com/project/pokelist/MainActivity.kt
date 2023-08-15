package com.project.pokelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.pokelist.presentation.PokemonDetailsScreen.PokemonDetailsScreen
import com.project.pokelist.presentation.PokemonListScreen
import com.project.pokelist.presentation.PokemonListScreen.PokemonListViewModel
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
                    val viewModel = hiltViewModel<PokemonListViewModel>()
                    val pokemons = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonListScreen.route
                    ) {
                        composable(
                            route = Screen.PokemonListScreen.route
                        ) {
                            PokemonListScreen(pokemons, navController)
                        }
                        composable(
                            route = Screen.PokemonDetailScreen.route + "/{pokemonId}",
                            arguments = listOf(
                                navArgument("pokemonId") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val pokemonId = remember {
                                it.arguments?.getInt("pokemonId") ?: 1
                            }
                            PokemonDetailsScreen(pokemonId, navController)
                        }
                    }

                }
            }
        }
    }
}
