package com.codesodja.androidpokedex.data.local.dao

import androidx.room.*
import com.codesodja.androidpokedex.data.local.model.FavoritePokedex
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePokedexDao {
    @Insert
    fun insertPokemon(pokemon: FavoritePokedex)

    @Insert
    fun insertLocalPokemonList(pokemons: List<FavoritePokedex>)

    @Update
    fun updateLocalPokemon(pokemon: FavoritePokedex)

    @Delete
    fun removePokemon(pokemon: FavoritePokedex)

    @Query("SELECT * FROM pokemon_table")
    fun getAllLocalPokemon(): Flow<List<FavoritePokedex>>

    @Query("DELETE FROM pokemon_table")
    fun removeAllLocalPokemon()
}