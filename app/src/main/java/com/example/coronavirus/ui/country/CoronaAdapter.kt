package com.example.coronavirus.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirus.data.CoronaCountryStats
import com.example.coronavirus.databinding.ItemCoronaStatsBinding

class CoronaAdapter : ListAdapter<CoronaCountryStats, CoronaAdapter.CoronaViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaViewHolder {
        return CoronaViewHolder(
            ItemCoronaStatsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    class CoronaViewHolder(private val binding: ItemCoronaStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(coronaCountryStats: CoronaCountryStats) {
            binding.coronaStats = coronaCountryStats
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<CoronaCountryStats>() {

    override fun areItemsTheSame(oldItem: CoronaCountryStats, newItem: CoronaCountryStats): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CoronaCountryStats, newItem: CoronaCountryStats): Boolean {
        return oldItem == newItem
    }
}