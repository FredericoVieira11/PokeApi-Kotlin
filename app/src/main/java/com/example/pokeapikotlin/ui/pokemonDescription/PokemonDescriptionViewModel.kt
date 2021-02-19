package com.example.pokeapikotlin.ui.pokemonDescription

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokeapikotlin.network.repository.pokemonDescription.IPokemonDescriptionRepository
import com.example.pokeapikotlin.network.request.PokemonDataRequest
import com.example.pokeapikotlin.network.resource.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PokemonDescriptionViewModel @ViewModelInject constructor(
    private val repository: IPokemonDescriptionRepository
): ViewModel() {

    fun getPokemonDescription(name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPokemonDescription(name = name)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun sendPokemonData(
            pokeImage: String,
            pokeId: Int,
            pokeName: String,
            pokeHeight: Int,
            pokeWeight: Int,
            pokeBaseExp: Int,
            pokeMove: String,
            pokeAbility: String,
            pokeType: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val pokemonData = PokemonDataRequest(
                pokeImage = pokeImage,
                pokeId = pokeId,
                pokeName = pokeName,
                pokeHeight = pokeHeight,
                pokeWeight = pokeWeight,
                pokeBaseExp = pokeBaseExp,
                pokeMove = pokeMove,
                pokeAbility = pokeAbility,
                pokeType = pokeType
        ).toJsonObject()
        try {
            emit(Resource.success(data = repository.sendPokemonData(pokemonData = pokemonData)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}