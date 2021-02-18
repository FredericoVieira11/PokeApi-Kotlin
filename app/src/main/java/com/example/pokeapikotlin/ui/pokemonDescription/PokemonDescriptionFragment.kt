package com.example.pokeapikotlin.ui.pokemonDescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapikotlin.R
import com.example.pokeapikotlin.databinding.FragmentPokemonDescriptionBinding
import com.example.pokeapikotlin.network.resource.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDescriptionBinding
    private lateinit var viewModel: PokemonDescriptionViewModel
    private val args: PokemonDescriptionFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPokemonDescriptionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PokemonDescriptionViewModel::class.java)
        getPokemonDescription()
        return binding.root
    }

    private fun getPokemonDescription() {
        viewModel.getPokemonDescription(name = args.passingName).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val id = resource.data?.body()?.id
                        val name = resource.data?.body()?.name
                        val height = resource.data?.body()?.height
                        val weight = resource.data?.body()?.weight
                        val baseExperience = resource.data?.body()?.base_experience
                        val moveName = resource.data?.body()!!.moves[0].move.name
                        val abilityName = resource.data?.body()!!.abilities[0].ability.name
                        val type = resource.data?.body()!!.types[0].type.name

                        val requestOptions = RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)

                        //Glide.with(this)
                        //    .applyDefaultRequestOptions(requestOptions)
                        //    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png")
                        //    .into()
                    }
                    Status.ERROR -> {
                        print("ERRO AO RESGATAR DADOS DA API !!!!!!!!!!!!! ERRO !!!!!!!")
                    }
                    Status.LOADING -> {
                        print("LOADING ...")
                    }
                }
            }
        })
    }

}