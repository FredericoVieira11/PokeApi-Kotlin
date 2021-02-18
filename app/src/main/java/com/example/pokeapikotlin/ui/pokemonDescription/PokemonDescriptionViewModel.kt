package com.example.pokeapikotlin.ui.pokemonDescription

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokeapikotlin.network.repository.pokemonDescription.IPokemonDescriptionRepository
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

}