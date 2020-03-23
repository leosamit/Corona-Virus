package com.example.coronavirus.ui

import androidx.lifecycle.ViewModel
import com.example.coronavirus.data.repository.CoronaRepository
import javax.inject.Inject

class CoronaStatsViewModel @Inject constructor(repository: CoronaRepository) : ViewModel() {

    init {
        repository.getCoronaSets()
    }

    val coronaStats = repository.coronaStats
}
