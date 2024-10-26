package com.example.weather.features.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.ApiClient
import com.example.weather.ConcreteLocalSource
import com.example.weather.FavoritesAdapter
import com.example.weather.ForecastModel
import com.example.weather.R
import com.example.weather.Repo
import com.example.weather.SharedVM
import com.example.weather.ViewModelFactory
import com.example.weather.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.launch


class Fragment_Favorite : Fragment(), FavoritesAdapter.OnSelectClick{

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var factory: ViewModelFactory
    //private lateinit var onDrawerClick: OnDrawerClick
    private lateinit var adapter: FavoritesAdapter
    private lateinit var sharedVM: SharedVM
    private var tempForecast: ForecastModel? = null
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleInit()
        adapter = FavoritesAdapter(mutableListOf(), this)
        sharedVM.getFavorites()

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val forecast = adapter.list[position]
                tempForecast = adapter.list[position]
                sharedVM.justDeleteFavorite(forecast)
                sharedVM.justInsertFavorite(tempForecast!!)
                //showRetrySnackbar(requireView(),"${forecast.timezone} has been deleted"){
                //    tempForecast?.let {
                //
                //    }
                //}
                adapter.removeItem(position)
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        binding.rvFavorites.visibility = View.INVISIBLE
        //onDrawerClick = activity as OnDrawerClick
        //binding.ivMore.setOnClickListener {
        //    onDrawerClick.onClick()
        //}




        binding.rvFavorites.adapter = adapter

        itemTouchHelper.attachToRecyclerView(binding.rvFavorites)
        lifecycleScope.launch {
            sharedVM.favorites.collect {
                hideLoading()
                binding.rvFavorites.visibility = View.VISIBLE
                adapter.updateList(it)
            }
        }
    }
    private fun handleInit() {
        factory =
            ViewModelFactory(Repo.getInstance(ApiClient, ConcreteLocalSource(requireContext())))
        sharedVM = ViewModelProvider(requireActivity(), factory)[SharedVM::class.java]
    }

    private fun showLoading() {
        binding.itemsShimmer.startShimmer()
        binding.itemsShimmer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.itemsShimmer.hideShimmer()
        binding.itemsShimmer.visibility = View.INVISIBLE
    }

    override fun onSelectClick(forecast: ForecastModel) {
        sharedVM.setSelectedForecast(forecast)
        findNavController().navigate(R.id.nav_home)
    }

}