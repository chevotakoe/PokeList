package com.project.pokelist.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.project.pokelist.data.PokemonApi
import com.project.pokelist.data.db.PokemonDatabase
import com.project.pokelist.data.db.PokemonEntity
import com.project.pokelist.data.dto.PokemonRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesPokemonDatabase(@ApplicationContext context: Context): PokemonDatabase{
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "poke.db"
        ).build()
    }
    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(PokemonApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    }
    @Provides
    @Singleton
    fun providePokemonPager(pokemonDb: PokemonDatabase, pokemonApi: PokemonApi): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                pokemonDb = pokemonDb,
                pokemonApi = pokemonApi
            ),
            pagingSourceFactory = {
                pokemonDb.dao.pagingSource()
            }
        )
    }


}
