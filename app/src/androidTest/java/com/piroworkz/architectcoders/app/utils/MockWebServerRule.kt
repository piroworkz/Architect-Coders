package com.piroworkz.architectcoders.app.utils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {
    lateinit var server: MockWebServer
    override fun starting(description: Description) {

        server = MockWebServer()
        server.start(9000)

    }

    override fun finished(description: Description) {
        server.shutdown()
    }
}