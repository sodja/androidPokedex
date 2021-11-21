package com.codesodja.androidpokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codesodja.androidpokedex.data.local.dao.FavoritePokedexDao
import com.codesodja.androidpokedex.data.local.model.FavoritePokedex

const val DATABASE_NAME = "favorite_store"


@Database(entities = [FavoritePokedex::class], version = 1)
abstract class PokedexRoomDatabase : RoomDatabase() {
    abstract fun pokedexDao(): FavoritePokedexDao?
}