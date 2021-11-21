package com.codesodja.androidpokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.compose.ui.res.colorResource
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codesodja.androidpokedex.R
import com.codesodja.androidpokedex.data.models.PokedexListEntry
import com.codesodja.androidpokedex.ui.viewmodels.PokedexListViewModel

class PokedexAdapter(private var pokedexItems: List<PokedexListEntry>, private var activity: FragmentActivity):
    RecyclerView.Adapter<PokedexAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokedexAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokedex_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PokedexAdapter.ViewHolder, position: Int) {
        viewHolder.pokeName.text = pokedexItems[position].pokemonName
        viewHolder.boxRelative.setBackgroundColor(activity.resources.getColor(R.color.TypeSteel))
            Glide.with(activity).load(pokedexItems[position].imageUrl).into(viewHolder.pokeImage)
    }

    override fun getItemCount(): Int {
        return pokedexItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokeName: TextView = itemView.findViewById(R.id.poke_name)
        val pokeImage: ImageView = itemView.findViewById(R.id.poke_image)
        val boxRelative: RelativeLayout = itemView.findViewById(R.id.items_box)

    }

    fun swapItems(invoiceItems: List<PokedexListEntry>){
        val diffCallback = PokedexDiffCallback(this.pokedexItems, invoiceItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        // clear contacts and add
        this.pokedexItems.toMutableList().clear()
        this.pokedexItems.toMutableList().addAll(invoiceItems)
        diffResult.dispatchUpdatesTo(this)
    }

}