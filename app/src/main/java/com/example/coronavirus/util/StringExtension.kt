package com.example.coronavirus.util

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("NewApi")
fun String.convertDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    val instant = Instant.ofEpochMilli(this.toLong())
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date) // 10/12/2019 06:35:45
}

