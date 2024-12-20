package com.example.weatherapp.features.favorites.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FavoriteItemBinding
import com.example.weatherapp.model.forecast.ForecastModel
import com.example.weatherapp.utils.getIconRes

class FavoritesAdapter(val list: MutableList<ForecastModel>, private val onSelectClick: OnSelectClick) : Adapter<FavoritesAdapter.FavoriteVH>() {

    inner class FavoriteVH(private val binding: FavoriteItemBinding) : ViewHolder(binding.root) {
        fun bind(item: ForecastModel) {
            binding.tvZone.text = item.timezone
            /*Glide.with(binding.root.context)
                .load("https://openweathermap.org/img/wn/${item.current?.weather?.get(0)?.icon}.png")
                .into(binding.ivIcon)*/
            binding.ivIcon.setImageResource(getIconRes(item.current?.weather?.get(0)?.icon?:""))
            binding.tvDes.text = item.current?.weather?.get(0)?.description ?: "no description"
            binding.root.setOnClickListener{
                onSelectClick.onSelectClick(item)
            }
        }
    }


    fun updateList(list: List<ForecastModel>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVH {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteVH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: FavoriteVH, position: Int) = holder.bind(list[position])


    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnSelectClick{
        fun onSelectClick(forecast: ForecastModel)
    }
}