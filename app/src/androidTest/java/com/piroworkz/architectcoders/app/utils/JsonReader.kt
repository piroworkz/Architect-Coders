package com.piroworkz.architectcoders.app.utils

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object JsonReader {
    fun MockResponse.read(): MockResponse {
        try {
            val inputStream =
                InstrumentationRegistry.getInstrumentation().context.assets.open("response.json")
            val builder = StringBuilder()
            InputStreamReader(inputStream, StandardCharsets.UTF_8)
                .readLines().forEach { builder.append(it) }
            return setBody(builder.toString())
        } catch (e: IOException) {
            throw e
        }
    }
}