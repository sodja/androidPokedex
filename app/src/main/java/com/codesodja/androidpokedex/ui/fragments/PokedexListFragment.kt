package com.codesodja.androidpokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codesodja.androidpokedex.R
import com.codesodja.androidpokedex.adapter.PokedexAdapter
import com.codesodja.androidpokedex.ui.viewmodels.PokedexListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexListFragment : Fragment() {

    companion object {
        fun newInstance() = PokedexListFragment()
    }

    private val viewModel by viewModels<PokedexListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokedex_list_fragment, container, false)

        var items = view.findViewById<RecyclerView>(R.id.pokedex_item)
        var imageView = view.findViewById<ImageView>(R.id.logo)
        viewModel.pokemonList.observe(requireActivity()) { response ->
            val adapter = PokedexAdapter(response, requireActivity())
            val curSize = adapter.itemCount
            adapter.notifyItemChanged(curSize)
            adapter.notifyItemInserted(0)
            items.scrollToPosition(0)
            items.adapter = adapter
            items.layoutManager = LinearLayoutManager(requireContext())
        }


        /*
        val tester  = view.findViewById<TextView>(R.id.tester)
       */
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProvider(this)[PokedexListViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {
        //  viewModel.loadPokedex()
        //  Log.d("currents", viewModel.pokemonList.value.toString())
    }
}