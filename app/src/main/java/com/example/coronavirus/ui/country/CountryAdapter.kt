package com.example.coronavirus.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirus.data.CoronaCountryStats
import com.example.coronavirus.databinding.ItemCoronaStatsBinding
import com.example.coronavirus.databinding.ItemCountryBinding
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class CountryAdapter : ListAdapter<String, CountryAdapter.CountryViewHolder>(
    CountryDiffCallback()
) {

    private val countryClicks: PublishSubject<String> = PublishSubject.create()
    val countryClicObs: Observable<String> =
        countryClicks.toFlowable(BackpressureStrategy.LATEST).toObservable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryAdapter.CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            , countryClicks
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class CountryViewHolder(
        private val binding: ItemCountryBinding,
        private val countryClick: PublishSubject<String>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(country: String) {
            binding.country = country
            binding.countryContainer.clicks().subscribe {
                Timber.d("Adapter Called")
                countryClick.onNext(country)
            }
        }
    }

}

class CountryDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

