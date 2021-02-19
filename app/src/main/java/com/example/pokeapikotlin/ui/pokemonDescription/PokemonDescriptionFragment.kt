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
import com.example.pokeapikotlin.MainActivity
import com.example.pokeapikotlin.R
import com.example.pokeapikotlin.databinding.FragmentPokemonDescriptionBinding
import com.example.pokeapikotlin.network.resource.Status
import com.example.pokeapikotlin.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDescriptionBinding
    private lateinit var viewModel: PokemonDescriptionViewModel
    private val args: PokemonDescriptionFragmentArgs by navArgs()
    private lateinit var pokeImage: String
    private var pokeId: Int? = null
    private lateinit var pokeName: String
    private var pokeHeight: Int? = null
    private var pokeWeight: Int? = null
    private var pokeBaseExp: Int? = null
    private lateinit var pokeMove: String
    private lateinit var pokeAbility: String
    private lateinit var pokeType: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPokemonDescriptionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PokemonDescriptionViewModel::class.java)
        getPokemonDescription()
        starClick()
        return binding.root
    }

    private fun getPokemonDescription() {
        viewModel.getPokemonDescription(name = args.passingName).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        hideProgressBar()
                        showItems()
                        val id = resource.data?.body()?.id
                        binding.txtPokeIdDescription.text = resource.data?.body()?.id.toString()
                        binding.txtPokeNameDescription.text = resource.data?.body()?.name
                        binding.txtPokeHeightDescription.text = resource.data?.body()?.height.toString()
                        binding.txtPokeWeightDescription.text = resource.data?.body()?.weight.toString()
                        binding.txtPokeBaseExpDescription.text = resource.data?.body()?.base_experience.toString()
                        binding.txtPokeMoveDescription.text = resource.data?.body()!!.moves[0].move.name
                        binding.txtPokeAbilityDescription.text = resource.data.body()!!.abilities[0].ability.name
                        binding.txtPokeTypeDescription.text = resource.data.body()!!.types[0].type.name

                        pokeImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                        pokeId = id
                        pokeName = resource.data.body()?.name.toString()
                        pokeHeight = resource.data.body()?.height
                        pokeWeight = resource.data.body()?.weight
                        pokeBaseExp = resource.data.body()?.base_experience
                        pokeMove = resource.data.body()!!.moves[0].move.name
                        pokeAbility = resource.data.body()!!.abilities[0].ability.name
                        pokeType = resource.data.body()!!.types[0].type.name

                        val requestOptions = RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)

                        Glide.with(this)
                            .applyDefaultRequestOptions(requestOptions)
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png")
                            .into(binding.imgPokemonDescription)

                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        hideItems()
                        showAlert(
                            activity,
                            binding.root,
                            getString(R.string.error_getting_data)
                        )
                    }
                    Status.LOADING -> {
                        showProgressBar()
                        hideItems()
                    }
                }
            }
        })
    }

    private fun sendPokemonData() {
        viewModel.sendPokemonData(
                pokeImage,
                pokeId!!,
                pokeName,
                pokeHeight!!,
                pokeWeight!!,
                pokeBaseExp!!,
                pokeMove,
                pokeAbility,
                pokeType
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data?.isSuccessful == true){
                            resource.data.body()
                        }
                        print("DADOS ENVIADOS")
                    }
                    Status.ERROR -> {
                        print("ERROR ALGUMA COISA DEU ERRADO !!!!")
                    }
                    Status.LOADING -> {
                        print("LOADING ...")
                    }
                }
            }
        })
    }

    private fun showProgressBar() {
        (requireActivity() as MainActivity).showProgressBar()
    }

    private fun hideProgressBar() {
        (requireActivity() as MainActivity).hideProgressBar()
    }

    private fun showItems() {
        binding.imgPokemonDescription.visibility = View.VISIBLE
        binding.txtPokeIdDescription.visibility = View.VISIBLE
        binding.txtPokeNameDescription.visibility = View.VISIBLE
        binding.txtPokeHeightDescription.visibility = View.VISIBLE
        binding.txtPokeWeightDescription.visibility = View.VISIBLE
        binding.txtPokeBaseExpDescription.visibility = View.VISIBLE
        binding.txtPokeMoveDescription.visibility = View.VISIBLE
        binding.txtPokeAbilityDescription.visibility = View.VISIBLE
        binding.txtPokeTypeDescription.visibility = View.VISIBLE
    }

    private fun hideItems() {
        binding.imgPokemonDescription.visibility = View.INVISIBLE
        binding.txtPokeIdDescription.visibility = View.INVISIBLE
        binding.txtPokeNameDescription.visibility = View.INVISIBLE
        binding.txtPokeHeightDescription.visibility = View.INVISIBLE
        binding.txtPokeWeightDescription.visibility = View.INVISIBLE
        binding.txtPokeBaseExpDescription.visibility = View.INVISIBLE
        binding.txtPokeMoveDescription.visibility = View.INVISIBLE
        binding.txtPokeAbilityDescription.visibility = View.INVISIBLE
        binding.txtPokeTypeDescription.visibility = View.INVISIBLE
    }

    private fun starClick() {
        var isStarOn = false
        binding.imgBtnStar.setOnClickListener {
            if (!isStarOn) {
                binding.imgBtnStar.setImageResource(R.drawable.ic_twotone_star_clicked_24)
                isStarOn = true
                sendPokemonData()
            } else {
                binding.imgBtnStar.setImageResource(R.drawable.ic_twotone_star_24)
                isStarOn = false
            }
        }
    }

}