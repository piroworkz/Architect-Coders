package com.piroworkz.architectcoders.app.utils

import com.piroworkz.architectcoders.app.utils.JsonReader.read
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object ServerDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        val path: String? = request.path
        return when {
            path?.contains("username=dlunac") == true -> MockResponse().read()
            else -> MockResponse()
        }
    }
}