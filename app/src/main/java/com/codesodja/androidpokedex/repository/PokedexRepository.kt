package com.codesodja.androidpokedex.repository

import com.codesodja.androidpokedex.data.remote.ApiInterface
import com.codesodja.androidpokedex.data.remote.SafeApiRequest
import com.codesodja.androidpokedex.data.remote.responses.Pokemon
import com.codesodja.androidpokedex.data.remote.responses.PokemonList
import com.codesodja.androidpokedex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val apiInterface: ApiInterface
): SafeApiRequest() {

    suspend fun getPokedexList(limit: Int, offset: Int): Flow<Resource<PokemonList>>{
        return flow<Resource<PokemonList>> {
            emit(safeApiRequest{ apiInterface.getPokemonList(limit, offset)})
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPokedexDetail(pokedexName: String): Flow<Resource<Pokemon>> {
        return flow<Resource<Pokemon>> {
            emit(safeApiRequest{ apiInterface.getPokemonDetails(pokedexName)})
        }.flowOn(Dispatchers.IO)
    }
}