package com.codesodja.androidpokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.produceState
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.codesodja.androidpokedex.R
import com.codesodja.androidpokedex.data.remote.responses.Pokemon
import com.codesodja.androidpokedex.ui.viewmodels.PokedexDetailViewModel
import com.codesodja.androidpokedex.utils.Resource

class PokedexDetailFragment : Fragment() {

    private val args : PokedexDetailFragmentArgs by navArgs()

    companion object {
        fun newInstance() = PokedexDetailFragment()
    }

    private lateinit var viewModel: PokedexDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokedex_detail_fragment, container, false)
        fetchData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokedexDetailViewModel::class.java)
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchData(){
        val pokemonName = args.pokedexname!!
        viewModel.getPokemonDetails(pokemonName)
        viewModel.pokedex.observe(requireActivity()){ response ->
            Log.d("details pokemon", response.toString())
        }
    }
}