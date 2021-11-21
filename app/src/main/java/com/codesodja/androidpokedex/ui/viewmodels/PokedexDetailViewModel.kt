package com.codesodja.androidpokedex.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesodja.androidpokedex.data.remote.responses.Pokemon
import com.codesodja.androidpokedex.repository.PokedexRepository
import com.codesodja.androidpokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexDetailViewModel @Inject constructor(
    private val repository: PokedexRepository
) : ViewModel() {

    private val _pokedex: MutableLiveData<Pokemon> = MutableLiveData()
    val pokedex: LiveData<Pokemon> = _pokedex

    fun getPokemonDetails(pokedexName: String) = viewModelScope.launch {
        repository.getPokedexDetail(pokedexName).collect { collection->
            when(collection){
                is Resource.Success->{
                   collection.data.let { pokemon->
                       _pokedex.value = pokemon
                   }
                }
                is Resource.Error->{}
                is Resource.Loading->{}
            }
        }
    }

}
