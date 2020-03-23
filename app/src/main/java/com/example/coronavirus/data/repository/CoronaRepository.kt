package com.example.coronavirus.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.coronavirus.api.CovidData
import com.example.coronavirus.api.ResultsResponse
import com.example.coronavirus.data.CoronaStats
import com.example.coronavirus.data.Result
import com.example.coronavirus.data.source.CoronaRemoteDataSource
import com.example.coronavirus.util.CoroutineDispatcherProvider
import com.example.coronavirus.util.postError
import com.example.coronavirus.util.postLoading
import com.example.coronavirus.util.postSuccess
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class CoronaRepository @Inject constructor(
    private val coronaRemoteDataSource: CoronaRemoteDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider
) {

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    private val statsLiveData: MutableLiveData<Result<List<CoronaStats>>> = MutableLiveData()
    val coronaStats: LiveData<Result<List<CoronaStats>>> = statsLiveData


//    fun fetchCoronaSets(): LiveData<Result<ResultsResponse<CoronaStats>?>> =
//        liveData(dispatcherProvider.ioDispatcher) {
//            //emit(Result.loading())
//
//            val responseStatus = coronaRemoteDataSource.fetchCoronaSets()
//            when (responseStatus.status) {
//                Result.Status.SUCCESS -> {
//                    emit(Result.success(responseStatus.data))
//                }
////                Result.Status.ERROR -> {
////                    emit(Result.error(responseStatus.message.toString()))
////                }
////                else -> emit(Result.error(responseStatus.message.toString()))
//            }
//        }

    fun getCoronaSets() {
        statsLiveData.postLoading()
        scope.launch {
            val responseStatus = coronaRemoteDataSource.fetchCoronaSets()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    statsLiveData.postSuccess(responseStatus.data!!.covidaData.coronaStats)
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo")
                    Timber.d("Error in Repo ${responseStatus.data?.message.toString()}")

                    statsLiveData.postError(responseStatus.message.toString())
                }

            }
        }
    }


}
