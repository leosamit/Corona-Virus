package com.example.coronavirus.util

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

class DateConverter {

    @SuppressLint("LongLogTag")
    @TargetApi(Build.VERSION_CODES.N)
    fun convertDate(
        dateInMilliseconds: String
    ): String? {
        Log.d("Samit dateInMilliseconds","Called")
        Log.d("Samit dateInMilliseconds",dateInMilliseconds.toString())
        var formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var dateString = formatter.format(Date(dateInMilliseconds))

        Log.d("Samit dateString",dateInMilliseconds.toString())

        return dateString;
    }
}