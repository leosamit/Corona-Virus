package com.example.coronavirus.util

import java.io.IOException


suspend fun safeApiCall(networkBlock: suspend () -> Unit, failureBlock: (Exception) -> Unit, errorMessage: String) {
    return try {
        networkBlock()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        failureBlock(IOException(errorMessage, e))
    }
}