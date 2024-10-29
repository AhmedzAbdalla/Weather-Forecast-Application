package com.example.weather

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather.databinding.AlertItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class AlertsAdapter(val list: MutableList<AlertItem>) : Adapter<AlertsAdapter.AlertsVH>() {

    inner class AlertsVH(private val binding: AlertItemBinding) : ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: AlertItem) {
            binding.tvLocation.text = item.locationName
            if (item.endDateTime==null) {
                binding.tvTime.text = binding.root.context.getString(R.string.at, localToReadable(item.startDateTime))
            }else{
                binding.tvTime.text = binding.root.context.getString(
                    R.string.from_to,
                    localToReadable(item.startDateTime),
                    localToReadable(item.startDateTime)
                )
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun localToReadable(localDateTime: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm", Locale.US)
            return localDateTime.format(formatter)
        }
    }


    fun updateList(list: List<AlertItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertsVH {
        val binding = AlertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlertsVH(binding)
    }

    override fun getItemCount() = list.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AlertsVH, position: Int) = holder.bind(list[position])

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}