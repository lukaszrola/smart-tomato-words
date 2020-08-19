package com.smart.tomato

import io.kotest.core.spec.style.StringSpec
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class WordsTest(private val application: EmbeddedApplication<*>): StringSpec({

    "test the server is running" {
        assert(application.isRunning())
    }
})
