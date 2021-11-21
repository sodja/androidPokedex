package com.codesodja.androidpokedex.adapter

import androidx.recyclerview.widget.DiffUtil
import com.codesodja.androidpokedex.data.models.PokedexListEntry

class PokedexDiffCallback(
    private val mOldList: List<PokedexListEntry>,
    private val mNewList: List<PokedexListEntry>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].number == mNewList[newItemPosition].number
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEntity = mOldList[oldItemPosition]
        val newEntity = mNewList[newItemPosition]

        return oldEntity.pokemonName == newEntity.pokemonName
    }
}