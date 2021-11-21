package com.codesodja.androidpokedex.ui.viewmodels

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.codesodja.androidpokedex.data.models.PokedexListEntry
import com.codesodja.androidpokedex.repository.PokedexRepository
import com.codesodja.androidpokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokedexListViewModel @Inject constructor(
    private val repository: PokedexRepository
) : ViewModel() {
    private var curPage = 0

    var _pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var pokemonList : MutableLiveData<List<PokedexListEntry>> = MutableLiveData()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)
    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokedex()
    }

    fun loadPokedex() {
        viewModelScope.launch {
            isLoading.value = true
            repository.getPokedexList(10, curPage * 10).collect { result->
                when (result) {
                    is Resource.Success -> {

                        endReached.value = curPage * 10 >= result.data!!.count
                        val pokedexEntries = result.data.results.mapIndexed{index, entry ->
                            val number = if (entry.url.endsWith("/")){
                                entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                            } else{
                                entry.url.takeLastWhile { it.isDigit() }
                            }
                            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                            PokedexListEntry(entry.name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            }, url , number.toInt() )
                        }
                        curPage++

                        loadError.value = ""
                        isLoading.value = false
                        _pokemonList.value += pokedexEntries
                        pokemonList.postValue(_pokemonList.value)
                    }
                    is Resource.Error -> {
                        loadError.value = result.message!!
                        isLoading.value = false
                    }
                    is Resource.Loading->{}
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calDominateColor(drawable: Drawable, onFinish: (android.graphics.Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(android.graphics.Color.valueOf(colorValue))
            }
        }
    }

    fun searchPokedexList(query: String) {
        val listToSearch = if (isSearchStarting) {
            _pokemonList.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                _pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val result = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if (isSearchStarting) {
                cachedPokemonList = _pokemonList.value
                isSearchStarting = false
            }
            _pokemonList.value = result
            isSearching.value = true
        }
    }
}