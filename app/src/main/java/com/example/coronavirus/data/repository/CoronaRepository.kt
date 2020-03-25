package com.example.coronavirus.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coronavirus.data.CoronaCountryStats
import com.example.coronavirus.data.Result
import com.example.coronavirus.data.source.CoronaRemoteDataSource
import com.example.coronavirus.util.CoroutineDispatcherProvider
import com.example.coronavirus.util.postError
import com.example.coronavirus.util.postLoading
import com.example.coronavirus.util.postSuccess
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class CoronaRepository @Inject constructor(
    private val coronaRemoteDataSource: CoronaRemoteDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider
) {

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    private val countryStatsLiveData: MutableLiveData<Result<List<CoronaCountryStats>>> =
        MutableLiveData()
    val coronaCountryStats: LiveData<Result<List<CoronaCountryStats>>> = countryStatsLiveData

    private val allDetailsLiveData: MutableLiveData<Result<CoronaCountryStats>> =
        MutableLiveData()
    val allCoronaDetails: LiveData<Result<CoronaCountryStats>> = allDetailsLiveData

    private val coronaByCountryLiveData: MutableLiveData<Result<CoronaCountryStats>> =
        MutableLiveData()
    val coronaByCountry: LiveData<Result<CoronaCountryStats>> = coronaByCountryLiveData


    fun getCountryCoronaList() {
        countryStatsLiveData.postLoading()
        scope.launch {
            val responseStatus = coronaRemoteDataSource.fetchCoronaCountrySets()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    countryStatsLiveData.postSuccess(responseStatus.data!!)
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo ${responseStatus.data?.toString()}")

                    countryStatsLiveData.postError(responseStatus.message.toString())
                }
            }
        }
    }

    fun getCountryCoronaListSoretd(sortBy: String) {
        countryStatsLiveData.postLoading()
        scope.launch {
            val responseStatus = coronaRemoteDataSource.fetchCoronaCountryListSoretd(sortBy)
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    countryStatsLiveData.postSuccess(responseStatus.data!!)
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo ${responseStatus.data?.toString()}")
                    countryStatsLiveData.postError(responseStatus.message.toString())
                }
            }
        }
    }

    fun getAllCoronaDetails() {
        allDetailsLiveData.postLoading()
        scope.launch {
            val responseStatus = coronaRemoteDataSource.fetchCoronaAllDetails()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    allDetailsLiveData.postSuccess(responseStatus.data!!)
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo")
                    Timber.d("Error in Repo ${responseStatus.data?.toString()}")
                    allDetailsLiveData.postError(responseStatus.message.toString())
                }
            }
        }
    }


    fun getCoronaByCountry(country: String) {
        coronaByCountryLiveData.postLoading()
        scope.launch {
            val responseStatus = coronaRemoteDataSource.fetchCoronaByCountry(country)
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    coronaByCountryLiveData.postSuccess(responseStatus.data!!)
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo")
                    Timber.d("Error in Repo ${responseStatus.data?.toString()}")
                    coronaByCountryLiveData.postError(responseStatus.message.toString())
                }
            }
        }
    }


}
