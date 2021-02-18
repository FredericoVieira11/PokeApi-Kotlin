package com.example.pokeapikotlin.di

import com.example.pokeapikotlin.network.repository.pokemonDescription.IPokemonDescriptionRepository
import com.example.pokeapikotlin.network.repository.pokemonDescription.PokemonDescriptionRepository
import com.example.pokeapikotlin.network.repository.pokemonList.IPokemonListRespository
import com.example.pokeapikotlin.network.repository.pokemonList.PokemonListRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModuleBinds {

    @Binds
    abstract fun bindPokemonListRepository(respository: PokemonListRespository): IPokemonListRespository

    @Binds
    abstract fun bindPokemonDescriptionRepository(repository: PokemonDescriptionRepository): IPokemonDescriptionRepository
}